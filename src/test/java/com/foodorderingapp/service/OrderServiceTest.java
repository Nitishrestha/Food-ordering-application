/*
package com.foodorderingapp.service;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.*;
import com.foodorderingapp.serviceImpl.OrdersServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderDAO orderDAO;

    @Mock
    private FoodService foodService;

    @Mock
    private OrderDetailService orderDetailService;

    @Mock
    private UserService userService;

    @InjectMocks
    OrdersServiceImpl ordersService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void add_thenResultOrder() {

        User user = new User();
        user.setUserId(1);
        Orders orders = new Orders();
        Food food = new Food(1, "momo", 100, new Restaurant());
        OrderDto orderDto = new OrderDto(1, new ArrayList<>());
        Restaurant restaurant = new Restaurant(1, "f1soft", "hattisar", "9817651648", new ArrayList<>());
        OrderDetail orderDetail = new OrderDetail();
        FoodQuantity foodQuantity = new FoodQuantity(food.getName(), food.getPrice(), restaurant.getName(), 1);

        when(userService.getUser(orderDto.getUserId())).thenReturn(user);
        when(orderDAO.add(orders)).thenReturn(orders);
        when(foodService.getFoodByResName(foodQuantity.getFoodName(), foodQuantity.getRestaurantName())).thenReturn(food);
        when(userService.update(user)).thenReturn(user);
        when(orderDetailService.add(new OrderDetail())).thenReturn(orderDetail);
        Assert.assertEquals(ordersService.add(orderDto), new BillDto());
    }

    @Test
    public void getUsersByUserId_thenResult_UserListMapperDto() {
       */
/* OrderLogDto1 userListDto = new OrderLogDto1();
        List<OrderLogDto1> userListDtoList=new ArrayList<>();
        userListDtoList.add(userListDto);
        UserLogMapperDto orderLogForAdminMapperDto1 = new UserLogMapperDto();
        orderLogForAdminMapperDto1.setOrderId(1);
        List<UserLogMapperDto> userListMapperDtoList= new ArrayList<>();
        userListMapperDtoList.add(orderLogForAdminMapperDto1);
        OrderDetail orderDetail=new OrderDetail("momo",100,"kfc",1);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetailList.add(orderDetail);*//*

        User user = new User();
        user.setUserId(1);
        UserLogMapperDto orderLogForAdminMapperDto1 = new UserLogMapperDto();
        orderLogForAdminMapperDto1.setOrderId(1);
        when(userService.getByUserId(user.getUserId())).thenReturn(Arrays.asList(new UserLogMapperDto()));
        when(orderDetailService.getOrderDetailByOrderId(anyInt())).thenReturn(Arrays.asList(new OrderDetail()));
        Assert.assertEquals(ordersService.getUsersByUserId(user.getUserId()),Arrays.asList(new OrderLogDto1()) );
    }

    @Test
    public void getOrder_thenResult_OrderListDto() {
       */
/* OrderListDto orderListDto=new OrderListDto(1,1,Arrays.asList(new FoodRes()),new Date());
        List<OrderListDto> orderListDtoList=new ArrayList<OrderListDto>();
        orderListDtoList.add(orderListDto);
        OrderDetail orderDetail=new OrderDetail("momo",100,"kfc",1);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetailList.add(orderDetail);
        OrderListMapperDto orderListMapperDto=new OrderListMapperDto(1,1,new Date());
        List<OrderListMapperDto> orderListMapperDtoList=new ArrayList<>();
        orderListMapperDtoList.add(orderListMapperDto);*//*

        when(orderDAO.getOrders()).thenReturn(Arrays.asList(new OrderListMapperDto()));
        when(orderDetailService.getOrderDetailByOrderId(anyInt())).thenReturn(Arrays.asList(new OrderDetail()));
        Assert.assertEquals(ordersService.getOrder(), Arrays.asList(new OrderListDto()));
    }

    @Test
    public void testToUpdateOrder() {
        Orders orders = new Orders();
        orders.setOrderId(1);
        when(orderDAO.getOrder(orders.getOrderId())).thenReturn(orders);
        when(orderDAO.update(orders)).thenReturn(true);
        Assert.assertEquals(ordersService.update(orders.getOrderId()), orders);
    }

    @Test
    public void add_whenGetUser_thenResultNull() {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("user not found.");
        ordersService.add(new OrderDto());
    }

    @Test
    public void add_whenGetFoodByResName_thenResultNull() {
        User user = new User();
        OrderDto orderDto = new OrderDto();
        FoodQuantityUtils foodQuantity = new FoodQuantityUtils("momo", 100, "kfc", 1);
        orderDto.setFoodList(Arrays.asList(foodQuantity));
        when(userService.getUser(orderDto.getUserId())).thenReturn(user);
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find food");
        ordersService.add(orderDto);
    }

    @Test
    public void add_whenFoodPriceDonotMatchedWithDatabaseFoodPriceColume_thenResultConflictException() {
        User user = new User();
        OrderDto orderDto = new OrderDto();
        Food food = new Food(1, "momo", 200, new Restaurant());
        FoodQuantityUtils foodQuantity = new FoodQuantityUtils("momo", 300, "kfc", 1);
        orderDto.setFoodList(Arrays.asList(foodQuantity));
        when(userService.getUser(orderDto.getUserId())).thenReturn(user);
        when(foodService.getFoodByResName(foodQuantity.getRestaurantName(), foodQuantity.getFoodName()))
                .thenReturn(food);
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("price not found");
        ordersService.add(orderDto);
    }

    @Test
    public void add_whenQuantityIsLessOrEqualToZero_thenResultConflictException() {
        User user = new User();
        OrderDto orderDto = new OrderDto();
        Food food = new Food(1, "momo", 200, new Restaurant());
        FoodQuantityUtils foodQuantity = new FoodQuantityUtils("momo", 200, "kfc", -1);
        orderDto.setFoodList(Arrays.asList(foodQuantity));
        when(userService.getUser(orderDto.getUserId())).thenReturn(user);
        when(foodService.getFoodByResName(foodQuantity.getRestaurantName(), foodQuantity.getFoodName()))
                .thenReturn(food);
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("quantity should be greater than 0");
        ordersService.add(orderDto);
    }

    @Test
    public void getUsersByUserId_whenGetByUserId_thenResultNull() {
        User user = new User();
        user.setUserId(1);
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("cannot find user.");
        ordersService.getUsersByUserId(user.getUserId());
    }

    @Test
    public void getUsersByUserId_whenGetOrderDetailByOrderId_thenResultNull() {
        User user = new User();
        user.setUserId(1);
        when(userService.getByUserId(user.getUserId())).thenReturn(Arrays.asList(new UserLogMapperDto()));
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderDetailList.");
        ordersService.getUsersByUserId(user.getUserId());
    }


    @Test
    public void getOrder_whenGetOrders_thenReturnNull() {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find order.");
        ordersService.getOrder();
    }

    @Test
    public void getOrder_whenGetOrderDetailByOrderId_thenReturnNull() {
        when(orderDAO.getOrders()).thenReturn(Arrays.asList(new OrderListMapperDto()));
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderDetailList.");
        ordersService.getOrder();
    }

    @Test
    public void update_whenGetOrder_thenResultNull() {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find order.");
        ordersService.update(anyInt());
    }
}

*/
