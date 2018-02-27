package com.foodorderingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.Application;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserControllerTest {


    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void addUser_whenAdded_thenReturnUser() throws Exception {

        UserDto userDto=new UserDto();
        userDto.setUserId(1);
        userDto.setEmail("ram@yahoo.com");
        userDto.setAddress("bkt");
        userDto.setFirstName("ram");
        userDto.setMiddleName("bahadur");
        userDto.setLastName("thapa");
        userDto.setUserPassword("ram");
        userDto.setContactNo("981646176");

        User user=new User();
        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setUserPassword(userDto.getUserPassword());
        user.setContactNo(userDto.getContactNo());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userDto);

        System.out.println(jsonString);
        given(userService.addUser(userDto)).willReturn(user);

        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();
        String outputInJson=response.getContentAsString();
        User returnedUser=mapper.readValue(outputInJson,User.class);
        assertThat(returnedUser).isEqualTo(user);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    public  void addUser_whenSomePropertyAreMissing_thenReturnBadRequest() throws Exception{

        UserDto userDto=new UserDto();
        userDto.setUserId(1);
        userDto.setAddress("bkt");
        userDto.setFirstName("ram");
        userDto.setMiddleName("bahadur");
        userDto.setLastName("thapa");
        userDto.setUserPassword("ram");
        userDto.setContactNo("981646176");

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userDto);
        System.out.println(jsonString);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }


}
