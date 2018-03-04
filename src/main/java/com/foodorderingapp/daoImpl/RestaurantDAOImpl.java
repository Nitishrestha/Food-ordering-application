package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RestaurantDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean deleteRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().delete(restaurant);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("restaurant doesn't exist");
        }
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().persist(restaurant);
            sessionFactory.getCurrentSession().flush();
            return restaurant;
        } catch (Exception e) {
            throw new BadRequestException("cannot add restaurant");
        }
    }

    public boolean updateRestaurant(Restaurant restaurant) {
        try {
            sessionFactory
                    .getCurrentSession()
                    .update(restaurant);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot update restaurant");
        }
    }

    public List<Restaurant> getAll() {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Restaurant", Restaurant.class)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DataNotFoundException("cannot find restaurants");
        }
    }

    @Override
    public List<Restaurant> getPaginatedRestaurantToUser(PageModel pageModel) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Restaurant where isActive = true",Restaurant.class);
        query.setFirstResult(pageModel.getFirstResult()*pageModel.getMaxResult());
        query.setMaxResults(pageModel.getMaxResult());
        List<Restaurant> restaurantList = query.getResultList();
        return restaurantList;
    }

    @Override
    public List<Restaurant> getPaginatedRestaurantToAdmin(PageModel pageModel) {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("From Restaurant",Restaurant.class);
    query.setFirstResult(pageModel.getFirstResult()*pageModel.getMaxResult());
    query.setMaxResults(pageModel.getMaxResult());
    List<Restaurant> restaurantList = query.getResultList();
    return restaurantList;
    }

    public Restaurant getRestaurantById(int id) {
        try {
            return sessionFactory.getCurrentSession().get(Restaurant.class, id);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("restaurant not found");
        }
    }

    public int deactivate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setActive(false);
        updateRestaurant(restaurant);
        return id;
    }

    public int activate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setActive(true);
        updateRestaurant(restaurant);
        return id;
    }

    public boolean getStatus(int id) {
        Restaurant restaurant =
                sessionFactory
                        .getCurrentSession()
                        .createQuery("FROM Restaurant where id= :id", Restaurant.class)
                        .setParameter("id", id)
                        .getSingleResult();
        return restaurant.isActive();
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Restaurant where name= :restaurantName", Restaurant.class)
                    .setParameter("restaurantName", restaurantName)
                    .getSingleResult();
        } catch (RuntimeException ex) {
            throw new DataNotFoundException("cannot find restaurantName");
        }
    }

    @Override
    public Long countRestaurant() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select count(1) from  Restaurant",Long.class)
                .getSingleResult();
    }

    @Override
    public long countActiveRestaurant() {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(1) from Restaurant where isActive=true",Long.class)
                .getSingleResult();
    }
}