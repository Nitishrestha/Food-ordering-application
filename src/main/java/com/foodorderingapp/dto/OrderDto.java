package com.foodorderingapp.dto;

import java.util.List;

public class OrderDto {

    private int userId;
    private List<FoodQuantity> foodList=null;

    public OrderDto(int userId, List<FoodQuantity> foodList) {
        this.userId = userId;
        this.foodList = foodList;
    }

    public OrderDto(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FoodQuantity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodQuantity> foodList) {
        this.foodList = foodList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        if (userId != orderDto.userId) return false;
        return foodList.equals(orderDto.foodList);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + foodList.hashCode();
        return result;
    }
}
