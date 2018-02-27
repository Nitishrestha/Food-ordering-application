package com.foodorderingapp.controller;

import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.Order.ORDER_API)
public class OrdersController {

    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersController(OrdersService ordersService,OrderDetailService orderDetailService){
        this.ordersService=ordersService;
        this.orderDetailService=orderDetailService;
    }

    @PostMapping
    public ResponseEntity<BillDto> addOrder(@RequestBody OrderDto orderDto) {
        BillDto billDto=ordersService.add(orderDto);
        return new ResponseEntity<BillDto>(billDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderListDto>> getOrder(){
        List<OrderListDto> orderListDtoList=ordersService.getOrder();
        return new ResponseEntity<List<OrderListDto>>(orderListDtoList,HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Orders> update(@PathVariable int orderId){
        Orders orders=ordersService.update(orderId);
        return new ResponseEntity<Orders>(orders,HttpStatus.OK);
    }

    @GetMapping(value = "/orderList/{userId}")
    public ResponseEntity<List<UserListDto>> getByUserId(@PathVariable("userId") int userId) {
        List<UserListDto> userListDtoList=ordersService.getUsersByUserId(userId);
        return new ResponseEntity<List<UserListDto>>(userListDtoList,HttpStatus.OK);
    }
}
