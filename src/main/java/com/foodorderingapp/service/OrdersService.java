package com.foodorderingapp.service;

import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrdersService {
    BillDto add(OrderDto orderDto);
    Orders updateConfirm(int orderId);
    Orders updateWatched(int orderId);
    List<OrderListDto> getOrderForAdminForMonth();
    List<OrderListDto> getOrderLogForAdminForToday();
    List<UserListDto> getUsersByUserForAMonth(int userId);
    List<UserListDto> getUsersByUserForToday(int userId);
}
