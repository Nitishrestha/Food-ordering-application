package com.foodorderingapp.dto;

import java.util.Date;
import java.util.List;

public class UserListDto {

    private int orderId;
    private int userId;
    private Date orderedDate;
    private Boolean confirm;
    private List<FoodRes> foodResList;

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
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
}
