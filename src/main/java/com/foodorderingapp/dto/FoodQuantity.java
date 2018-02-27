package com.foodorderingapp.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class FoodQuantity {

    @NotBlank(message = "This field is required.")
    @Size(min=1,max=20,message = "food name must be between 2 and 20.")
    private String foodName;
    @NotBlank(message = "This field is required.")
    private double foodPrice;
    @NotBlank(message = "This field is required.")
    @Size(min=1,max=10,message = "restaurant name must be between 2 and 20.")
    private String restaurantName;
    @NotBlank(message = "This field is required.")
    private int quantity;

    public FoodQuantity(String foodName, double foodPrice, String restaurantName, int quantity) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.restaurantName = restaurantName;
        this.quantity = quantity;
    }

    public FoodQuantity() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodQuantity that = (FoodQuantity) o;

        if (Double.compare(that.foodPrice, foodPrice) != 0) return false;
        if (quantity != that.quantity) return false;
        if (foodName != null ? !foodName.equals(that.foodName) : that.foodName != null) return false;
        return restaurantName != null ? restaurantName.equals(that.restaurantName) : that.restaurantName == null;
    }

    @Override
    public int hashCode() {
        int result = foodName != null ? foodName.hashCode() : 0;
        result = 31 * result + (restaurantName != null ? restaurantName.hashCode() : 0);
        return result;
    }
}
