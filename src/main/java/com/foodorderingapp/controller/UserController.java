package com.foodorderingapp.controller;

import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserDtoResponse;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.User.*;

@RestController
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> add(@RequestBody @Valid UserDto userDto) {
        UserDtoResponse user = userService.addUser(userDto);
        System.out.println(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getUsers() {
        List<UserDtoResponse> userDtoResponseList = userService.getUsers();
        return new ResponseEntity(userDtoResponseList, HttpStatus.OK);
    }

    @PostMapping(VERIFY_USER)
    public ResponseEntity<LoginDto> verifyUser(@RequestBody @Valid LoginDto loginDto) {
        LoginDto loginDto1 = userService.verifyUser(loginDto.getUserPassword(), loginDto.getEmail());
        return new ResponseEntity(loginDto1, HttpStatus.OK);
    }

    @GetMapping(GET_USER_BY_ID)
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<String> getUserPreviousBalance() {
        userService.updateBalance();
        return new ResponseEntity("balance updated", HttpStatus.OK);
    }
}
