package com.foodorderingapp.responseDTO;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by TOPSHI KREATS on 2/27/2018.
 */
public class FoodResponseDTO {

    @NotBlank(message = "This field is required.")
    @Size(min=4,max=15,message = "first name must be between 2 and 20.")
    private String name;

    @NotBlank(message = "This field is required.")
    private double price;

    private int restaurantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
