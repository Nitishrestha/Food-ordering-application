package com.foodorderingapp.service;

import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail add(OrderDetail orderDetail);
    List<OrderDetailDto> getOrderDetails();
    List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
