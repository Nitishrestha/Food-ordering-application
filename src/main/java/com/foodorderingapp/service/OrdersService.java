package com.foodorderingapp.service;

import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrdersService {
    BillDto add(OrderDto orderDto);
    List<OrderListDto> getOrder();
    Orders update(int orderId);
    List<UserListDto> getUsersByUserId(int userId);
}
