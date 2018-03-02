package com.foodorderingapp.controller;


import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.MockFood;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.exception.DataNotFoundException;
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

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getOrderDetail() {
        List<OrderDetailDto> orderDetailDtoList = orderDetailService.getOrderDetails();
        return new ResponseEntity<>(orderDetailDtoList, HttpStatus.OK);
    }


    @GetMapping(value = "/log/{userId}/paginate/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getPaginatedOrderLogToUser(@PathVariable int userId, @PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel(firstResult, maxResult);
        List<MockFood> foodList = orderDetailService.getPaginatedOrderLogToUser(pageModel, userId);
        if (foodList == null && foodList.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setPageModel(pageModel);
        genericResponse.setResponseData(foodList);
        long count = orderDetailService.countOrderDetail(userId);
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


    @GetMapping(value = "admin/log/paginate/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getPaginatedOrderLogToAdmin(@PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel(firstResult, maxResult);
        List<MockFood> foodList = orderDetailService.getPaginatedOrderLogToAdmin(pageModel);
        if (foodList == null && foodList.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setPageModel(pageModel);
        genericResponse.setResponseData(foodList);
        long count = orderDetailService.countOrderDetail();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/order/{userId}/today")
    public List<MockFood> getCurrentDateOrderLog(@PathVariable int userId) {
        return orderDetailService.getCurrentDateFoodLog(userId);
    }

}