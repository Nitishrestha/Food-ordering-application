package com.foodorderingapp.controller;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.foodorderingapp.commons.WebUrlConstant.Restaurant.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RESTAURANT)
public class RestaurantController {

    private static RestaurantService restaurantService;
    private static FoodService foodService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant) {
        Restaurant restaurant1 = restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<>(restaurant1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantService.getAll();
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @GetMapping(GET_FOOD_BY_RESTAURANT)
    public ResponseEntity<List<Food>> getFoodsByRestaurant(@PathVariable int id) {
        List<Food> foodList = foodService.getFoodByRestaurantId(id);
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping(ACTIVATE_RESTAURANT)
    public int activateRestaurant(@PathVariable int id) {
        restaurantService.activate(id);
        return id;
    }

    @GetMapping(DEACTIVATE_RESTAURANT)
    public int deactivateRestaurant(@PathVariable int id) {
        restaurantService.deactivate(id);
        return id;
    }

    @GetMapping(GET_PAGINATED_RESTAURANT_TO_ADMIN)
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

    @GetMapping(GET_PAGINATED_RESTAURANT_TO_USER)
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
        long count = restaurantService.countActiveRestaurant();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(GET_PAGINATED_FOOD)
    public ResponseEntity<GenericResponse> getPaginatedFood(@PathVariable int id, @PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel(firstResult, maxResult);
        List<Food> foodList = foodService.getPaginatedFood(pageModel, id);
        if (foodList == null && foodList.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }
        GenericResponse genericResponse = new GenericResponse(pageModel, foodList);
        long count = foodService.countFood(id);
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


    @GetMapping(GET_RESTAURANT_BY_ID)
    public Restaurant getRestaurantById(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping(DELETE_RESTAURANT_BY_ID)
    public int deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(getRestaurantById(id));
        return id;
    }

    @PutMapping(UPDATE_RESTAURANT_BY_ID)
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        restaurantService.updateRestaurant(restaurant, id);
        return restaurant;
    }
}