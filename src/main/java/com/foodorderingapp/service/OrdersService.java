package com.foodorderingapp.service;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrdersService {
    BillDto add(OrderDto orderDto);
    List<OrderListMapperDto> getOrderLogForAdminForAMonth(PageModel pageModel);
    List<OrderListMapperDto> getOrderLogForAdminForToday();
    List<UserLogMapperDto> getUsersByUserForAMonth(PageModel pageModel,int userId);
    List<UserLogMapperDto> getUsersByUserForToday(int userId);
    Orders updateConfirm(int orderId);
    Orders updateWatched(int orderId);
    List<OrderDetail> getOrderDetailByUserForToday(int userId);
    Long countOrder();
}
