package com.foodorderingapp.service;

import com.foodorderingapp.dto.*;
import com.foodorderingapp.model.User;
import sun.rmi.runtime.Log;

import java.util.List;

public interface UserService {
    User addUser(UserDto userDto);
    List<User> getUsers();
    LoginDto verifyUser(String userPassword,String email);
    User getUser(int userId);
    User update(User user);
    List<UserListMapperDto> getByUserId(int userId);
}
