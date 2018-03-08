package com.foodorderingapp.model;

import com.foodorderingapp.dto.OrderListMapperDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_orders")
@SqlResultSetMapping(
        name="OrderMapping",
        classes =
                {@ConstructorResult(targetClass = OrderListMapperDto.class,
                        columns = {
                                @ColumnResult(name="order_id", type=Integer.class),
                                @ColumnResult(name="user_id", type=Integer.class),
                                @ColumnResult(name="first_name",type=String.class),
                                @ColumnResult(name="middle_name",type=String.class),
                                @ColumnResult(name="last_name",type=String.class),
                                @ColumnResult(name="ordered_date",type=Date.class),
                                @ColumnResult(name="confirm",type=Boolean.class)
                        })})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id",nullable=false,updatable = false)
    private int orderId;
    @Column(name="confirm")
    private Boolean confirm=false;
    @Column(name="watched")
    private Boolean watched=false;
    @Column(name="ordered_date")
    @Temporal(TemporalType.DATE)
    private Date orderedDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

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

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }


}
