package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
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
    public  OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO){
        this.orderDetailDAO=orderDetailDAO;
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        OrderDetail orderDetail1= orderDetailDAO.add(orderDetail);
        if(orderDetail1==null){
            throw new DataNotFoundException("cannot add orderDetail.");
        }else{
            return orderDetail1;
        }
    }

    public List<OrderDetailDto> getOrderDetails() {
        List<OrderDetailDto> orderDetails = orderDetailDAO.getOrderDetail();
        if(orderDetails==null || orderDetails.size()==0){
            throw new DataNotFoundException("cannot find orderDetailDto.");
        }else{
            return orderDetails;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {

        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailByOrderId(orderId);
        if(orderDetails==null || orderDetails.size()==0){
            throw new DataNotFoundException("cannot find orderDetail.");
        }else{
            return orderDetails;
        }
    }
}

