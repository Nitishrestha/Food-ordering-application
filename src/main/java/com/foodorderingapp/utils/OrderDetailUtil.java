package com.foodorderingapp.utils;

import com.foodorderingapp.dto.MockFood;
import com.foodorderingapp.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOPSHI KREATS on 3/2/2018.
 */
public class OrderDetailUtil {

    public static List<MockFood> getMockFoodList(List<OrderDetail> orderDetailList) {
        List<MockFood> mockFoodList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            MockFood mockFood = new MockFood();
            mockFood.setOrderId(orderDetail.getOrders().getOrderId());
            mockFood.setOrderDetailId(orderDetail.getOrderDetailId());
            mockFood.setFoodName(orderDetail.getFoodName());
            mockFood.setRestaurantName(orderDetail.getRestaurantName());
            mockFood.setQuantity(orderDetail.getQuantity());
            mockFood.setFoodPrice(orderDetail.getFoodPrice());
            mockFood.setDate(orderDetail.getOrders().getDate());
            mockFoodList.add(mockFood);
        }
        return mockFoodList;
    }
}
