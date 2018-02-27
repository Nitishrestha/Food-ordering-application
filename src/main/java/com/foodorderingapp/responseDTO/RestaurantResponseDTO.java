package com.foodorderingapp.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodorderingapp.model.Food;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by TOPSHI KREATS on 2/27/2018.
 */
public class RestaurantResponseDTO {

    @NotNull(message = "please enter the restaurant name")
    @Size(min=3,max=25)
    private String name;

    @NotBlank(message = "please enter the restaurant address")
    @Size(min=3,max=25)
    private String address;

    @NotBlank(message = "please enter the restaurant contact")
    @Size(min=7,max=10)
    private String contact ;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Food> foodList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

}
