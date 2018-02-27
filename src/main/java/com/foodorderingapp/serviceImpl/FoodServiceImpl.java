package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.service.FoodService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodServiceImpl implements FoodService{

    private final FoodDAO foodDAO;
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodDAO,RestaurantDAO restaurantDAO){
            this.foodDAO=foodDAO;
            this.restaurantDAO=restaurantDAO;
    }

    public boolean deleteFood(Food food) {
        if (food==null) {
            throw new DataNotFoundException("cannot delete food.");
        } else {
            return foodDAO.deleteFood(food);
        }
    }

    public boolean updateFood(Food food,int id) {
        Food food1 = foodDAO.getFoodById(id);
        if(food1==null){
            throw new DataNotFoundException("cannot find food.");
        }
        food1.setName(food.getName());
        food1.setPrice(food.getPrice());
        return foodDAO.updateFood(food1);
    }

    public List<Food> getAll() {
        List<Food> foodList= foodDAO.getAll();
        if(foodList==null ){
            throw new DataNotFoundException("cannot find foodlist.");
        }else{
            return foodList;
        }
    }

    public Food getFoodById(int id) {
        Food food= foodDAO.getFoodById(id);
        if(food==null){
            throw new DataNotFoundException("cannot find food.");
        }else{
            return food;
        }
    }

    public List<Food> getFoodByRestaurantId(int id) {
        List<Food> foodList=foodDAO.getFoodByRestaurantId(id);
        if(foodList==null || foodList.size()==0){
            throw new DataNotFoundException("cannot find foodList.");
        }
        else{
            return foodList;
        }
    }

    public List<Food> addFoodsToRestaurant(List<Food> foodList) {

        if (foodList == null || foodList.size()==0) {
            throw new DataNotFoundException("cannot add foodList.");
        } else {
            return foodDAO.addFoodsToRestaurant(foodList);
        }
    }

    @Override
    public Food getFoodByResName(String restaurantName, String foodName) {

        Food food=foodDAO.getFoodByResName(restaurantName,foodName);
        if(food==null){
            throw new DataNotFoundException("cannot find foodList1.");
        }else{
            return food;
        }
    }

    @Override
    public List<Food> getPaginatedFood(PageModel pageModel, int id) {
        return foodDAO.getPaginatedFood(pageModel,id);
    }

    @Override
    public long countFood(int id) {
        return foodDAO.countFood(id);
    }
}
