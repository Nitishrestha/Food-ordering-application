package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.StyledEditorKit;
import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    @Autowired
    public RestaurantServiceImpl(RestaurantDAO restaurantDAO){
        this.restaurantDAO=restaurantDAO;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
       Restaurant restaurant1= restaurantDAO.addRestaurant(restaurant);
        if (restaurant1==null) {
            throw new DataNotFoundException("cannot add restaurant.");
        } else {
            return restaurant1;
        }
    }

    public boolean deleteRestaurant(Restaurant restaurant) {
        boolean b=restaurantDAO.deleteRestaurant(restaurant);
        if (b==false) {
            throw new UserConflictException("cannot delete restaurant.");
        } else {
            return true;
        }
    }

    public boolean updateRestaurant(Restaurant restaurant, int id) {
        Restaurant restaurant1 =restaurantDAO.getRestaurantById(id);
        if(restaurant1==null){
            throw new UserConflictException("cannot update restaurant.");
        }
        restaurant1.setName(restaurant.getName());
        restaurant1.setAddress(restaurant.getAddress());
        restaurant1.setContact(restaurant.getContact());
        return restaurantDAO.updateRestaurant(restaurant1);
    }
    public List<Restaurant> getAll() {

        List<Restaurant> restaurantList= restaurantDAO.getAll();
        if(restaurantList==null || restaurantList.size()==0){
            throw new DataNotFoundException("cannot find restaurantList.");
        }else{
            return restaurantList;
        }
    }

    @Override
    public List<Restaurant> getPaginatedRestaurantToUser(PageModel pageModel) {
        return restaurantDAO.getPaginatedRestaurantToUser(pageModel);
    }

    @Override
    public List<Restaurant> getPaginatedRestaurantToAdmin(PageModel pageModel) {
        return restaurantDAO.getPaginatedRestaurantToAdmin(pageModel);
    }

    public Restaurant getRestaurantById(int id) {
        Restaurant restaurant= restaurantDAO.getRestaurantById(id);
        if(restaurant==null){
            throw new DataNotFoundException("cannot find restaurant.");
        }else{
            return restaurant;
        }
    }

    public int deactivate(int id) {
        if(!restaurantDAO.getRestaurantById(id).equals(null)){
            if(getStatus(id)!=false) {
                restaurantDAO.deactivate(id);
            }
        }
        return id;
    }

    public int activate(int id) {
        if(!restaurantDAO.getRestaurantById(id).equals(null)){
            if(getStatus(id)!=true) {
                restaurantDAO.activate(id);
            }
        }
        return id;
    }

    public boolean getStatus(int id) {
        return restaurantDAO.getStatus(id);
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {

        Restaurant restaurant= restaurantDAO.getRestaurantByName(restaurantName);
        if(restaurant==null){
            throw new DataNotFoundException("cannot find restaurantName.");
        }else{
            return  restaurant;
        }
    }
    @Override
    public Long countRestaurant(){
        return  restaurantDAO.countRestaurant();
    }

    @Override
    public long countActiveRestaurant() {
        return restaurantDAO.countActiveRestaurant();
    }
}