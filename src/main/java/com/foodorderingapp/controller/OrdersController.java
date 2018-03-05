package com.foodorderingapp.controller;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.dto.UserLogMapperDto;
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

    /*@GetMapping(value = "/test/orderLogs/{userId}")
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

    @GetMapping(TODAY_ORDER_TO_ADMIN)
    public ResponseEntity<List<OrderListMapperDto>> getOrderLogForAdminForToday() {
        List<OrderListMapperDto> orderListMapperDtoList = ordersService.getOrderLogForAdminForToday();
        return new ResponseEntity<>(orderListMapperDtoList, HttpStatus.OK);
    }

    @GetMapping(MONTHLY_ORDER_TO_ADMIN)
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


    @GetMapping(MONTHLY_ORDER_TO_USER)
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

    @GetMapping(TODAY_ORDER_TO_USER)
    public ResponseEntity<List<UserLogMapperDto>> getByUserForPaticularDay(@PathVariable("userId") int userId) {
        List<UserLogMapperDto> userLogMapperDtoList = ordersService.getUsersByUserForToday(userId);
        return new ResponseEntity<>(userLogMapperDtoList, HttpStatus.OK);
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
