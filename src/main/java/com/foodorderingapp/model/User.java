package com.foodorderingapp.model;

import com.foodorderingapp.dto.UserLogMapperDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity

@Table(name="tbl_users")
@SqlResultSetMapping(
        name="UserMapping",
        classes =
                {@ConstructorResult(targetClass = UserLogMapperDto.class,
                        columns = {
                                @ColumnResult(name="food_name", type=String.class),
                                @ColumnResult(name="restaurant_name", type=String.class),
                                @ColumnResult(name="food_price", type=Integer.class),
                                @ColumnResult(name="ordered_date",type=Date.class),
                        })})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",nullable=false,updatable = false)
    private int userId;
    @NotNull(message = "Can't be null.")
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="email")
    private String email;
    @Column(name="contact_no")
    private String contactNo;
    @Column(name="address")
    private String address;
    @Column(name="user_role")
    private String userRole="user";
    @Column(name="balance")
    private double balance = 1200;

    public User(String firstName, String middleName, String lastName, String userPassword,
                String email, String contactNo, String address, String userRole, double balance) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.userRole = userRole;
        this.balance = balance;
    }

    public User(){

    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = userPassword != null ? userPassword.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
