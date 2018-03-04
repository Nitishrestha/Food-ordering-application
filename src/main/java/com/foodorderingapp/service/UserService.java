package com.foodorderingapp.service;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserDtoResponse;

import java.util.List;

public interface UserService {
    UserDtoResponse addUser(UserDto userDto);
    List<UserDtoResponse> getUsers();
    LoginDto verifyUser(String userPassword,String email);
    User getUser(int userId);
    User update(User user);
    List<UserLogMapperDto> getByUserForAMonth(PageModel pageModel,int userId);
    List<UserLogMapperDto> getByUserForToday(int userId);
}
