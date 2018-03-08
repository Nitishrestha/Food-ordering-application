package com.foodorderingapp.service;

import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.dto.UserLogMapperDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserDtoResponse;

import java.util.List;

public interface UserService {
    UserDtoResponse addUser(UserDto userDto);
    List<UserDtoResponse> getUsers();
    LoginDto verifyUser(String userPassword,String email);
    User getUser(int userId);
    User update(User user);
    List<UserListMapperDto> getUsersByUserForAMonth(int userId);
    List<UserListMapperDto> getByUserForToday(int userId);
//    Double getLastMonthBalanceByUserId(int userId);
}
