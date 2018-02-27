package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDAOImpl implements FoodDAO {

    private final SessionFactory sessionFactory;
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public FoodDAOImpl(SessionFactory sessionFactory, RestaurantDAO restaurantDAO) {
        this.sessionFactory = sessionFactory;
        this.restaurantDAO = restaurantDAO;
    }

    public boolean deleteFood(Food food) {
        try {
            sessionFactory.getCurrentSession().delete(food);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot delete food");
        }
    }

    public boolean updateFood(Food food) {
        try {
            sessionFactory.getCurrentSession().update(food);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot update food");
        }
    }

    public List<Food> getAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM Food", Food.class)
                .getResultList();
    }

    public Food getFoodById(int id) {
            if(sessionFactory.getCurrentSession().get(Food.class, id)==null){
                throw new DataNotFoundException("cannot find food");
            }else{
                return  sessionFactory.getCurrentSession().get(Food.class, id);
            }
    }


    public List<Food> getFoodByRestaurantId(int id) {
        String query = "FROM Food WHERE restaurant= :restaurant";
        Restaurant restaurant = restaurantDAO.getRestaurantById(id);
        System.out.println("food dao called");
        System.out.println(restaurant);
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Food.class)
                .setParameter("restaurant", restaurant)
                .getResultList();
    }

    public List<Food> addFoodsToRestaurant(List<Food> foodList) {
        try {
            for (Food food : foodList) {
                food.getRestaurant().setId(food.getRestaurantId());
                sessionFactory
                        .getCurrentSession()
                        .persist(food);
            }
            sessionFactory.getCurrentSession().flush();
            return foodList;

        } catch (Exception e) {
            throw new DataNotFoundException("cannot add food to restaurant");
        }
    }

    public Food getFoodByResName(String restaurantName ,String foodName) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Food where restaurant.name=:restaurantName and name=:foodName", Food.class)
                    .setParameter("restaurantName", restaurantName)
                    .setParameter("foodName",foodName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("foodName or restaurant is not in the list");
        }
    }

    @Override
    public List<Food> getPaginatedFood(PageModel pageModel, int id) {
       Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Food where restaurant.id = :id",Food.class)
                        .setParameter("id",id);

        query.setFirstResult(pageModel.getFirstResult()* pageModel.getMaxResult());
        List<Food> foodList = query.getResultList();
        return foodList;
    }

    @Override
    public long countFood(int id) {
        return  sessionFactory.getCurrentSession().createQuery("select count(1) from Food where restaurant.id = :id",Long.class)
                .setParameter("id",id)
                .getSingleResult();
    }
}