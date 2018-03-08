package com.foodorderingapp.service;

import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail add(OrderDetail orderDetail);
    List<OrderDetailDto> getOrderDetails();
    List<OrderDetail> getOrderDetailByOrderId(int orderId);
//    List<OrderDetail> getOrderDetailByUserId(int userId);
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
//   OrderDetail getOrdetailByRFId(String foodName,String restaurantName,int orderId);
    OrderDetail getOrderDetailByUserId(int userId,String foodName,String restaurantName);


    }
