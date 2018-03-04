package com.foodorderingapp.dto;

import com.foodorderingapp.model.Food;

import java.util.ArrayList;
import java.util.List;

public class BillDto {

    private double balance;
    private List<Food> foodList;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillDto billDto = (BillDto) o;

        if (Double.compare(billDto.balance, balance) != 0) return false;
        return foodList != null ? foodList.equals(billDto.foodList) : billDto.foodList == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(balance);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (foodList != null ? foodList.hashCode() : 0);
        return result;
    }
}
