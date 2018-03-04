package com.foodorderingapp.dto;

import com.foodorderingapp.model.Food;

import java.util.Date;
import java.util.List;

public class UserListDto {

    private Date orderedDate;
    private List<FoodRes> foodResList;

    public UserListDto() {
    }

    public UserListDto(Date orderedDate,List<FoodRes> foodResList) {
        this.orderedDate = orderedDate;
        this.foodResList = foodResList;
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
