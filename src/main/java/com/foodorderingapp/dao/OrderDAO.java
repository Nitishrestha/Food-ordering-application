package com.foodorderingapp.dao;

import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrderDAO {
     Orders add(Orders orders);
     List<OrderListMapperDto> getOrders();
     Boolean update(Orders orders);
     Orders getOrder(int orderId);
}
