/*
package com.foodorderingapp.service;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.serviceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void addUser_thenResultUser(){
        UserDto userDto=new UserDto();
        userDto.setEmail("ram@ayahoo.com");
        when(userDAO.getUserByEmailId(userDto.getEmail())).thenReturn(null);
        when(userDAO.addUser(new User())).thenReturn(new User());
        Assert.assertEquals(userService.addUser(new UserDto()),new User());
    }

    @Test
    public void getList_thenReturnUserList(){
        when(userDAO.getUsers()).thenReturn(Arrays.asList(new User()));
        Assert.assertEquals(userService.getUsers(),Arrays.asList(new User()));
    }

    @Test
    public void verifyUser_thenResultLoginDto(){
        LoginDto loginDto=new LoginDto("ram","ram@yahoo.com","user",1200);
        User user=new User();
        user.setUserPassword("ram");
        user.setEmail("ram@yahoo.com");
        System.out.println(loginDto);
        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(user.getUserPassword(),user.getUserPassword())).thenReturn(true);
        Assert.assertEquals(userService.verifyUser(user.getUserPassword(),user.getEmail()),loginDto);

    }

    @Test
    public void getUser_thenResultUser(){
        User user=new User();
        user.setUserId(1);
        when(userDAO.getUser(user.getUserId())).thenReturn(user);
        Assert.assertEquals(userService.getUser(user.getUserId()),user);
    }

    @Test
    public void updateUser_thenResultUser(){
        User user=new User();
        user.setUserId(1);
        when(userDAO.getUser(user.getUserId())).thenReturn(user);
        when(userDAO.update(user)).thenReturn(true);
        Assert.assertEquals(userService.update(user),user);
    }


    @Test
    public void add_whenGetUserByEmailId_thenResultUser(){
        User user=new User();
        user.setEmail("hari1@yahoo.com");
        UserDto dto=new UserDto();
        dto.setEmail("hari1@yahoo.com");
        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(user);
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("user already exit.");
        userService.addUser(dto);
    }

    @Test
    public void verifyUser_whenGetUserByEmailId_thenResultNull(){
        User user=new User();
        user.setUserPassword("ram");
        user.setEmail("rr");
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("email not found.");
        userService.verifyUser(user.getUserPassword(),user.getEmail());
    }

    @Test
    public void verifyUser_whenPasswordEncoderReturnsFalse(){
        User user=new User();
        user.setUserPassword("ram");
        user.setEmail("rr");
        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(user);
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("userpassword didnt match.");
        userService.verifyUser(user.getUserPassword(),user.getEmail());
    }

    @Test
    public void getUsers_thenResult(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find userList.");
        userService.getUsers();
    }

    @Test
    public void getUser_whenGetUser_thenResultNull(){
        User user=new User();
        user.setUserId(1);
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("user not found.");
        userService.getUser(user.getUserId());
    }

    @Test
    public void updateUser_thenResultFalse(){
        User user=new User();
        user.setUserId(1);
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("cannot update user.");
        userService.update(user);
    }
}
*/
