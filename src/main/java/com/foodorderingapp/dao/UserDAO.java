package com.foodorderingapp.dao;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.dto.UserLogMapperDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.User;

import java.util.List;

public interface UserDAO {
    User addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
//    List<UserLogMapperDto> getByUserForCurrentMonth(PageModel pageModel, int userId);
    User getUserByEmailId(String email);
    Boolean update(User user);
    List<UserListMapperDto> getUsersByUserForAMonth(int userId);
    List<UserLogMapperDto> getByUserForToday(int userId);

}
