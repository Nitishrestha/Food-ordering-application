package com.foodorderingapp.dao;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import java.util.List;

public interface OrderDetailDAO {
     OrderDetail add(OrderDetail orderDetail);
     List<OrderDetailDto> getOrderDetail();
     List<OrderDetail> getOrderDetailByOrderId(int orderId);

     Long countOrderDetail(int userId);
     List<OrderDetail>getPaginatedOrderLogToUser(PageModel pageModel, int userId);
     List<OrderDetail> getPaginatedOrderLogToAdmin(PageModel pageModel);
     Long countOrderDetail();
     List<OrderDetail> getCurrentDateFoodLog(int userId);
     List<OrderDetail> getPaginatedCurrentMonthFoodLog(int userId);
}
