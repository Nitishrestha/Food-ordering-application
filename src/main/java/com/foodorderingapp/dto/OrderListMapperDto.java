package com.foodorderingapp.dto;

import java.util.Date;

public class OrderListMapperDto {

    private int orderId;
    private int userId;
    private Date orderedDate;

    public OrderListMapperDto(int orderId, int userId, Date orderedDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderedDate = orderedDate;
    }

    public OrderListMapperDto(){

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

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
