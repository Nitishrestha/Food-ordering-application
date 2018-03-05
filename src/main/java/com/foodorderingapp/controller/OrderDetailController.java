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
        return new ResponseEntity<>(orderDetailDtoList, HttpStatus.OK);
    }

    @GetMapping(GET_ORDER_DETAIL_BY_USER_ID)
    public ResponseEntity<List<OrderDetail>> getOrderDetailByUserId(@PathVariable("userId") int userId)
    {
        List<OrderDetail> orderDetailList=orderDetailService.getOrderDetailByUserId(userId);
        return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }
}