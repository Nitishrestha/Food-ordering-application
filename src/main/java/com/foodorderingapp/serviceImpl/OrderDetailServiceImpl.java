package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.MockFood;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.utils.OrderDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO) {
        this.orderDetailDAO = orderDetailDAO;
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        OrderDetail orderDetail1 = orderDetailDAO.add(orderDetail);
        if (orderDetail1 == null) {
            throw new DataNotFoundException("cannot add orderDetail.");
        } else {
            return orderDetail1;
        }
    }

    public List<OrderDetailDto> getOrderDetails() {
        List<OrderDetailDto> orderDetails = orderDetailDAO.getOrderDetail();
        if (orderDetails == null || orderDetails.size() == 0) {
            throw new DataNotFoundException("cannot find orderDetailDto.");
        } else {
            return orderDetails;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {

        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailByOrderId(orderId);
        if (orderDetails == null || orderDetails.size() == 0) {
            throw new DataNotFoundException("cannot find orderDetail.");
        } else {
            return orderDetails;
        }
    }


    @Override
    public Long countOrderDetail(int userId) {
        return orderDetailDAO.countOrderDetail(userId);
    }

    @Override
    public Long countOrderDetail() {
        return orderDetailDAO.countOrderDetail();
    }

    @Override
    public List<MockFood> getPaginatedOrderLogToUser(PageModel pageModel, int userId) {
        List<OrderDetail> orderDetailList = orderDetailDAO.getPaginatedOrderLogToUser(pageModel, userId);
        return OrderDetailUtil.getMockFoodList(orderDetailList);
    }

    @Override
    public List<MockFood> getPaginatedOrderLogToAdmin(PageModel pageModel) {
        List<OrderDetail> orderDetailList = orderDetailDAO.getPaginatedOrderLogToAdmin(pageModel);
        return OrderDetailUtil.getMockFoodList(orderDetailList);
    }

    @Override
    public List<MockFood> getCurrentDateFoodLog(int userId) {
        List<OrderDetail> orderDetailList = orderDetailDAO.getCurrentDateFoodLog(userId);
        return OrderDetailUtil.getMockFoodList(orderDetailList);
    }
}

