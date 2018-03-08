package com.foodorderingapp.controller;

import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.foodorderingapp.commons.WebUrlConstant.OrderDetail.*;
import java.util.List;

@RestController
@RequestMapping(ORDER_DETAIL)
public class OrderDetailController {


    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService){
        this.orderDetailService=orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getOrderDetail()
    {
        List<OrderDetailDto> orderDetailDtoList=orderDetailService.getOrderDetails();
        return new ResponseEntity<>(orderDetailDtoList, HttpStatus.OK);
    }

}