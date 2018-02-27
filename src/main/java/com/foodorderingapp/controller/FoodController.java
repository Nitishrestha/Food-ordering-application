package com.foodorderingapp.controller;

import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */
@RestController
@RequestMapping(WebUrlConstant.Food.FOOD_API)
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    //GET ALL FOODS
    @GetMapping
    public ResponseEntity<List<Food>> allFood(){
        List<Food> foodList= foodService.getAll();
        return new ResponseEntity<List<Food>>(foodList, HttpStatus.OK);
    }

    //DELETE FOOD FROM ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable int id){
        foodService.deleteFood(foodService.getFoodById(id));
        return new ResponseEntity<String>("Food has been deleted successfully",HttpStatus.OK);
    }

    //EDIT FOOD FROM ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFood(@RequestBody Food food,@PathVariable int id){
        foodService.updateFood(food, id);
        return new ResponseEntity<String>("Food updated successfully!",HttpStatus.OK) ;
    }

    //GET FOOD FROM ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Food> getFoodBydId(@PathVariable int id){
        Food food= foodService.getFoodById(id);
        return new ResponseEntity<Food>(food,HttpStatus.OK);
    }

    //POST LIST OF FOODS
    @PostMapping
    public ResponseEntity<List<Food>> addFoods(@RequestBody List<Food> foodList){
        List<Food> foodList1=foodService.addFoodsToRestaurant(foodList);
        return new ResponseEntity<List<Food>>(foodList1,HttpStatus.OK);
    }
}