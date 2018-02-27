package com.foodorderingapp.model;

import com.foodorderingapp.dto.OrderDetailDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_order_detail")
@SqlResultSetMapping(
        name="OrderDetailMapping",
        classes =
                {@ConstructorResult(targetClass = OrderDetailDto.class,
                        columns = {
                @ColumnResult(name="order_id", type=Integer.class),
                @ColumnResult(name="first_name",type=String.class),
                @ColumnResult(name="middle_name",type=String.class),
                @ColumnResult(name="last_name" ,type=String.class),
                @ColumnResult(name="food_name" ,type=String.class),
                @ColumnResult(name="food_price",type=Double.class ),
                @ColumnResult(name="restaurant_name" ,type=String.class),
                @ColumnResult(name="quantity",type=Integer.class),
                @ColumnResult(name="ordered_date",type=Date.class)
                        })})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_detail_id",nullable=false,updatable = false)
    private int orderDetailId;
    @Column(name="food_name")
    private String foodName;
    @Column(name="food_price")
    private double foodPrice;
    @Column(name="restaurant_name")
    private String restaurantName;
    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;


    public OrderDetail(String foodName, double foodPrice, String restaurantName, int quantity) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.restaurantName = restaurantName;
        this.quantity = quantity;
    }

    public OrderDetail(){

    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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

        OrderDetail that = (OrderDetail) o;

        return Double.compare(that.foodPrice, foodPrice) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(foodPrice);
        return (int) (temp ^ (temp >>> 32));
    }
}
