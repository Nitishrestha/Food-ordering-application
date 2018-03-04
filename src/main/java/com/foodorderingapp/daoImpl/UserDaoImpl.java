package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.UserLogMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    private final SessionFactory sessionFactory;
    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory, OrderDetailDAO orderDetailDAO) {
        this.sessionFactory = sessionFactory;
        this.orderDetailDAO = orderDetailDAO;
    }

    public User addUser(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
            return user;
        } catch (Exception ex) {
            throw new DataNotFoundException("cannot add user");
        }
    }

    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
    }

    public User getUser(int userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public User getUserByEmailId(String email) {
        try {
            User user1 = sessionFactory.getCurrentSession().
                    createQuery("FROM User WHERE email=:email", User.class).
                    setParameter("email", email).
                    getSingleResult();
            return user1;
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<UserLogMapperDto> getByUserForCurrentMonth(PageModel pageModel,int userId) {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_order_detail.food_name,tbl_order_detail.food_price,tbl_order_detail.restaurant_name," +
                        " tbl_orders.ordered_date from(( tbl_order_detail \n" +
                        "INNER JOIN tbl_orders ON tbl_orders.order_id=tbl_order_detail.order_id)\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id) " +
                        "WHERE YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date)\n" +
                        "AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date) " +
                        "AND tbl_users.user_id=? AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC", "UserMapping")
                .setParameter(1, userId);
        qry.setFirstResult(pageModel.getFirstResult()*pageModel.getMaxResult());
        qry.setMaxResults(pageModel.getMaxResult());
        List<UserLogMapperDto> userLogMapperDtoList=qry.getResultList();
        return userLogMapperDtoList;
    }

    public List<UserLogMapperDto> getByUserForToday(int userId) {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_order_detail.food_name,tbl_order_detail.food_price," +
                        "tbl_order_detail.restaurant_name, tbl_orders.ordered_date from(( tbl_order_detail \n" +
                        "INNER JOIN tbl_orders ON tbl_orders.order_id=tbl_order_detail.order_id)\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id)" +
                        "WHERE CAST(tbl_orders.ordered_date AS DATE)=CURRENT_DATE \n" +
                        "AND tbl_users.user_id=? AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC","UserMapping")
                .setParameter(1, userId);
        return qry.getResultList();
    }
}

