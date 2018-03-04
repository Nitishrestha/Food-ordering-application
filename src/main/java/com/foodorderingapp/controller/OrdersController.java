package com.foodorderingapp.controller;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.model.OrderDetail;
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

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

/*
    @GetMapping(value = "/test/orderLogs/{userId}")
    public ResponseEntity<List<OrderDetail>> getUserForToday(@PathVariable("userId") int userId) {
        List<OrderDetail> orderDetailList =ordersService.getOrderDetailByUserForToday(userId);
        return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }
*/


    @PostMapping
    public ResponseEntity<BillDto> addOrder(@RequestBody OrderDto orderDto) {
        BillDto billDto = ordersService.add(orderDto);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }


    @GetMapping(value = "/admin/orderLog/today")
    public ResponseEntity<List<OrderListMapperDto>> getOrderLogForAdminForToday() {
        List<OrderListMapperDto> orderListMapperDtoList = ordersService.getOrderLogForAdminForToday();
        return new ResponseEntity<>(orderListMapperDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/orderLog/month/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getOrderLogForAdminForAMonth(@PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        List<OrderListMapperDto> orderListMapperDtoList = ordersService.getOrderLogForAdminForAMonth(pageModel);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(orderListMapperDtoList);
        genericResponse.setPageModel(pageModel);
        long count = ordersService.countOrder();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


    @GetMapping(value = "/userList/{userId}/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getByUserForAMonth(@PathVariable int firstResult,
                                                              @PathVariable int maxResult, @PathVariable("userId") int userId) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        List<UserLogMapperDto> userListDtoList = ordersService.getUsersByUserForAMonth(pageModel, userId);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(userListDtoList);
        genericResponse.setPageModel(pageModel);
        long count = ordersService.countOrder();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<UserLogMapperDto>> getByUserForPaticularDay(@PathVariable("userId") int userId) {
        List<UserLogMapperDto> userLogMapperDtoList = ordersService.getUsersByUserForToday(userId);
        return new ResponseEntity<>(userLogMapperDtoList, HttpStatus.OK);
    }

    @PutMapping("/confirm/{orderId}")
    public ResponseEntity<Orders> updateConfirm(@PathVariable int orderId) {
        Orders orders = ordersService.updateConfirm(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/watched/{orderId}")
    public ResponseEntity<Orders> updateWatched(@PathVariable int orderId) {
        Orders orders = ordersService.updateWatched(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
