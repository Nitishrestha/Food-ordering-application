package com.foodorderingapp.service;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.MockFood;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail add(OrderDetail orderDetail);
    List<OrderDetailDto> getOrderDetails();
    List<OrderDetail> getOrderDetailByOrderId(int orderId);

    Long countOrderDetail(int userId);
    Long countOrderDetail();
    List<MockFood>getPaginatedOrderLogToUser(PageModel pageModel, int userId);
    List<MockFood> getPaginatedOrderLogToAdmin(PageModel pageModel);
    List<MockFood> getCurrentDateFoodLog(int userId);
    List<MockFood> getPaginatedCurrentMonthFoodLog(int userId);
}
