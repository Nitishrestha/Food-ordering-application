package com.foodorderingapp.model;

import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.dto.UserListMapperDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_orders")

@SqlResultSetMapping(
        name="OrderMapping",
        classes =
                {@ConstructorResult(targetClass = OrderListMapperDto.class,
                        columns = {
                                @ColumnResult(name="order_id", type=Integer.class),
                                @ColumnResult(name="user_id", type=Integer.class),
                                @ColumnResult(name="ordered_date",type=Date.class),
                        })})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id",nullable=false,updatable = false)
    private int orderId;
    @Column(name="confirm")
    private Boolean confirm;


    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
