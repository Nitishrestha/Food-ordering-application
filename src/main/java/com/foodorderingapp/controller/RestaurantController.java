package com.foodorderingapp.controller;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.Restaurant.RESTAURANT_API)
public class RestaurantController {

    private static RestaurantService restaurantService;
    private static FoodService foodService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList=restaurantService.getAll();
        return  new ResponseEntity<>(restaurantList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant) {
        Restaurant restaurant1=restaurantService.addRestaurant(restaurant);
        return  new ResponseEntity<>(restaurant1,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping(value = "/{id}")
    public int deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(getRestaurantById(id));
        return id;
    }

    @PutMapping(value = "/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        restaurantService.updateRestaurant(restaurant, id);
        return restaurant;
    }

    @GetMapping(value = "/{id}/foods")
    public ResponseEntity<List<Food>> getFoodsByRestaurant(@PathVariable int id) {
        List<Food> foodList=foodService.getFoodByRestaurantId(id);
        return new ResponseEntity<>(foodList,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/activate")
    public int activateRestaurant(@PathVariable int id) {
        restaurantService.activate(id);
        return id;
    }

    @GetMapping(value = "/{id}/deactivate")
    public int deactivateRestaurant(@PathVariable int id) {
        restaurantService.deactivate(id);
        return id;
    }

    @GetMapping(value = "admin/page/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getPaginatedRestaurantToAdmin(@PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        List<Restaurant> restaurant = restaurantService.getPaginatedRestaurantToAdmin(pageModel);
        if (restaurant == null && restaurant.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(restaurant);
        genericResponse.setPageModel(pageModel);
        long count = restaurantService.countRestaurant();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/page/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getPaginatedRestaurantToUser(@PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        List<Restaurant> restaurant = restaurantService.getPaginatedRestaurantToUser(pageModel);
        if (restaurant == null && restaurant.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(restaurant);
        genericResponse.setPageModel(pageModel);
        long count = restaurantService.countRestaurant();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/page/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getPaginatedFood(@PathVariable int id, @PathVariable int firstResult,@PathVariable int maxResult) {
        PageModel pageModel = new PageModel(firstResult,maxResult);
        List<Food> foodList = foodService.getPaginatedFood(pageModel,id);
        if (foodList == null && foodList.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }
        GenericResponse genericResponse = new GenericResponse(pageModel,id);
        long count = foodService.countFood(id);
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}