package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserDtoResponse;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.userDAO = userDAO;

    }

    public UserDtoResponse addUser(UserDto userDto) {

        User user1 = userDAO.getUserByEmailId(userDto.getEmail());
        if (user1 == null) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setContactNo(userDto.getContactNo());
            user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
            user.setAddress(userDto.getAddress());
            user.setEmail(userDto.getEmail());
            userDAO.addUser(user);

            UserDtoResponse userDtoResponse=new UserDtoResponse();
            BeanUtils.copyProperties(user, userDtoResponse);
            return userDtoResponse;

        } else {
            throw new UserConflictException("user already exit.");
        }
    }

    public List<UserDtoResponse> getUsers() {
        List<User> userList= userDAO.getUsers();

        if(userList==null || userList.size()==0){
            throw new DataNotFoundException("cannot find userList.");
        }else{
            List<UserDtoResponse> userDtoResponseList=new ArrayList<>();
            for(User user:userList){
                UserDtoResponse userDtoResponse=new UserDtoResponse();
                BeanUtils.copyProperties(user, userDtoResponse);
                userDtoResponseList.add(userDtoResponse);
            }
            return userDtoResponseList;
        }
    }

    public LoginDto verifyUser(String userPassword,String email) {

        User user = userDAO.getUserByEmailId(email);
        if (user == null) {
            throw new DataNotFoundException("email not found.");
        } else if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
            LoginDto loginDto = new LoginDto();
            BeanUtils.copyProperties(user, loginDto);
            System.out.println(loginDto);
            return loginDto;
        } else {
            throw new UserConflictException("userpassword didnt match.");
        }
    }

    public User getUser(int userId) {
        User user= userDAO.getUser(userId);
        if(user==null){
            throw new DataNotFoundException("user not found.");
        }
        return user;
    }

    public User update(User user) {
        if(userDAO.update(user)==true) {
            return user;
        }else{
            throw new UserConflictException("cannot update user.");
        }
    }

    @Override
    public List<UserListMapperDto> getUsersByUserForAMonth(int userId) {
        return  userDAO.getUsersByUserForAMonth(userId);
    }

/*
    @Override
    public List<UserLogMapperDto> getByUserForAMonth(PageModel pageModel,int userId) {
       return userDAO.getByUserForCurrentMonth(pageModel,userId);
    }
*/

    @Override
    public List<UserLogMapperDto> getByUserForToday(int userId) {
        return userDAO.getByUserForToday(userId);
    }
}
