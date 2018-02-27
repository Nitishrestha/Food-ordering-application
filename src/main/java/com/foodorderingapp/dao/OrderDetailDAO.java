package com.foodorderingapp.dao;

import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import java.util.List;

public interface OrderDetailDAO {
     OrderDetail add(OrderDetail orderDetail);
     List<OrderDetailDto> getOrderDetail();
     List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
