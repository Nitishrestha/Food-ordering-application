package com.foodorderingapp.controller;

import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.Order.*;

@RestController
@RequestMapping(ORDER)
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<BillDto> addOrder(@RequestBody OrderDto orderDto) {
        BillDto billDto = ordersService.add(orderDto);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }

    @GetMapping(TODAY_ORDER_TO_ADMIN)
    public ResponseEntity<List<OrderListDto>> getOrderLogForAdminForToday() {
        List<OrderListDto> orderListMapperDtoList = ordersService.getOrderLogForAdminForToday();
        return new ResponseEntity<>(orderListMapperDtoList, HttpStatus.OK);
    }

    @GetMapping(MONTHLY_ORDER_TO_ADMIN)
    public ResponseEntity<List<OrderListDto>> getOrderLogForAdminForAMonth() {
        List<OrderListDto> orderListDtoList = ordersService.getOrderForAdminForMonth();
        return new ResponseEntity<>(orderListDtoList, HttpStatus.OK);
    }

    @GetMapping(MONTHLY_ORDER_TO_USER)
    public ResponseEntity<List<UserListDto>> getByUserForAMonth(@PathVariable("userId") int userId) {
        List<UserListDto> userListDtoList = ordersService.getUsersByUserForAMonth(userId);
        return new ResponseEntity<>(userListDtoList, HttpStatus.OK);
    }

    @GetMapping(TODAY_ORDER_TO_USER)
    public ResponseEntity<List<UserListDto>> getByUserForParticularDay(@PathVariable("userId") int userId) {
        List<UserListDto> userListDtoList = ordersService.getUsersByUserForToday(userId);
        return new ResponseEntity<>(userListDtoList, HttpStatus.OK);
    }

    @PutMapping(CONFIRM)
    public ResponseEntity<Orders> updateConfirm(@PathVariable int orderId) {
        Orders orders = ordersService.updateConfirm(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping(WATCHED)
    public ResponseEntity<Orders> updateWatched(@PathVariable int orderId) {
        Orders orders = ordersService.updateWatched(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
