package com.foodorderingapp.dto;

import java.util.Date;

public class OrderListMapperDto {

    private  String firstName;
    private  String lastName;
    private String foodName;
    private String restaurantName;
    private int foodPrice;
    private Date orderedDate;

    public OrderListMapperDto(String firstName, String lastName, String foodName,
                              String restaurantName, int foodPrice, Date orderedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.foodName = foodName;
        this.restaurantName = restaurantName;
        this.foodPrice = foodPrice;
        this.orderedDate = orderedDate;
    }

    public OrderListMapperDto(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
