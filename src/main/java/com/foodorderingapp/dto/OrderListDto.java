package com.foodorderingapp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderListDto {

    private int orderId;
    private int userId;
    private List<FoodRes> foodResList=null;
    private Date orderedDate;

    public OrderListDto(int orderId, int userId, List<FoodRes> foodResList, Date orderedDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.foodResList = foodResList;
        this.orderedDate = orderedDate;
    }

    public OrderListDto(){

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FoodRes> getFoodResList() {
        return foodResList;
    }

    public void setFoodResList(List<FoodRes> foodResList) {
        this.foodResList = foodResList;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderListDto that = (OrderListDto) o;

        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }
}
