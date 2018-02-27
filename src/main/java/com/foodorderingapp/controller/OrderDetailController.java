package com.foodorderingapp.controller;


import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.OrderDetail.ORDER_DETAIL_API)
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrdersService ordersService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService,OrdersService ordersService){
        this.orderDetailService=orderDetailService;
        this.ordersService=ordersService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getOrderDetail()
    {
        List<OrderDetailDto> orderDetailDtoList=orderDetailService.getOrderDetails();
        return new ResponseEntity<List<OrderDetailDto>>(orderDetailDtoList, HttpStatus.OK);
    }
}