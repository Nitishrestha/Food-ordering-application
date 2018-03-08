package com.foodorderingapp.dto;

import java.util.Date;

public class UserListMapperDto {
    private int orderId;
    private int userId;
    private Date orderedDate;

    public UserListMapperDto(){

    }

    public UserListMapperDto(int orderId, int userId, Date orderedDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderedDate = orderedDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
