package com.foodorderingapp.controller;

import com.foodorderingapp.commons.FoodOrderingMessageConstant;
import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.foodorderingapp.commons.WebUrlConstant.Food.*;
import static com.foodorderingapp.commons.FoodOrderingMessageConstant.*;
/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */
@RestController
@RequestMapping(FOOD)
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<List<Food>> addFoods(@RequestBody List<Food> foodList) {
        List<Food> foodList1 = foodService.addFoodsToRestaurant(foodList);
        return new ResponseEntity<>(foodList1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Food>> allFood() {
        List<Food> foodList = foodService.getAll();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping(GET_FOOD_BY_ID)
    public ResponseEntity<Food> getFoodBydId(@PathVariable int id) {
        Food food = foodService.getFoodById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_FOOD_BY_ID)
    public ResponseEntity<String> deleteFood(@PathVariable int id) {
        foodService.deleteFood(foodService.getFoodById(id));
        return new ResponseEntity<>(DELETE_FOOD_MESSAGE, HttpStatus.OK);
    }

    @PutMapping(UPDATE_FOOD_BY_ID)
    public ResponseEntity<String> updateFood(@RequestBody Food food, @PathVariable int id) {
        foodService.updateFood(food, id);
        return new ResponseEntity<>(UPDATE_FOOD_MESSAGE, HttpStatus.OK);
    }
}