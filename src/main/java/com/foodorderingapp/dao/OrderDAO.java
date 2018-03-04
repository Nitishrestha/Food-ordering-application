package com.foodorderingapp.dao;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.dto.OrderLogDto1;
import com.foodorderingapp.dto.UserLogMapperDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrderDAO {
     Orders add(Orders orders);
     List<OrderListMapperDto> getOrders();
     Boolean update(Orders orders);
     Orders getOrder(int orderId);
     List<OrderListMapperDto> getOrderLogForAdminForAMonth(PageModel pageModel);
     List<OrderListMapperDto> getOrderLogForAdminForToday();
     List<OrderDetail> getOrderDetailByUserForToday(int userId);
      Long countOrder();
}
