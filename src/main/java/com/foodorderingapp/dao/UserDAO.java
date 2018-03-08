package com.foodorderingapp.dao;

import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.dto.UserLogMapperDto;
import com.foodorderingapp.model.User;

import java.util.List;

public interface UserDAO {
    User addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
    User getUserByEmailId(String email);
    Boolean update(User user);
    List<UserListMapperDto> getUsersByUserForAMonth(int userId);
    List<UserListMapperDto> getByUserForToday(int userId);
//    Double getLastMonthBalanceByUserId(int userId);

}
