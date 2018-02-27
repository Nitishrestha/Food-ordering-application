package com.foodorderingapp.controller;

import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.User.USER_API)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody @Valid UserDto userDto) {
        User user=userService.addUser(userDto);
        System.out.println(user);
        return new ResponseEntity(user , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList= userService.getUsers();
        return new ResponseEntity(userList , HttpStatus.OK);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<LoginDto> verifyUser(@RequestBody @Valid LoginDto loginDto) {
        LoginDto loginDto1 = userService.verifyUser(loginDto.getUserPassword(), loginDto.getEmail());
        return new ResponseEntity(loginDto1 , HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
        User user=userService.getUser(userId);
        return new ResponseEntity(user , HttpStatus.OK);
    }
}
