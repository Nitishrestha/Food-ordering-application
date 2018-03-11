package com.foodorderingapp.utils;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderUtil {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserService userService;


    public OrderListDto getOrderListMapperDtoList(OrderListMapperDto orderListMapperDto) {
        OrderListDto orderListDto = new OrderListDto();
        List<FoodRes> foodResList = new ArrayList<>();
        orderListDto.setOrderId(orderListMapperDto.getOrderId());
        orderListDto.setUserId(orderListMapperDto.getUserId());
        orderListDto.setFirstName(orderListMapperDto.getFirstName());
        orderListDto.setMiddleName(orderListMapperDto.getMiddleName());
        orderListDto.setLastName(orderListMapperDto.getLastName());
        orderListDto.setConfirm(orderListMapperDto.getConfirm());
        orderListDto.setOrderedDate(orderListMapperDto.getOrderedDate());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(orderListMapperDto.getOrderId());
        for (OrderDetail orderDetail : orderDetailList) {
            foodResList.add(FoodResUtil.addFoodRes(orderDetail));
            orderListDto.setFoodResList(foodResList);
        }
        return orderListDto;
    }

    public UserListDto getUserListDto(UserListMapperDto userListMapperDto) {
        UserListDto userListDto = new UserListDto();
        List<FoodRes> foodResList = new ArrayList<>();
        userListDto.setUserId(userListMapperDto.getUserId());
        userListDto.setOrderId(userListMapperDto.getOrderId());
        userListDto.setOrderedDate(userListMapperDto.getOrderedDate());
        userListDto.setConfirm(userListMapperDto.getConfirm());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(userListMapperDto.getOrderId());
        for (OrderDetail orderDetail : orderDetailList) {
            foodResList.add(FoodResUtil.addFoodRes(orderDetail));
            userListDto.setFoodResList(foodResList);
        }
        return userListDto;
    }

}