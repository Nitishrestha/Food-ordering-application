package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
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
            System.out.println(user1);
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
            return  false;
        }
    }

    public List<UserListMapperDto> getByUserId(int userId) {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_orders.order_id ,tbl_orders.ordered_date, tbl_users.first_name ," +
                        "tbl_users.middle_name,tbl_users.last_name,tbl_orders.user_id \n" +
                        "FROM tbl_orders\n" +
                        "INNER JOIN  tbl_users ON tbl_orders.user_id=tbl_users.user_id  \n" +
                        "WHERE tbl_orders.user_id=?\n" +
                        "AND tbl_orders.confirm=true")
                .setParameter(1, userId);
        return qry.getResultList();
    }
}

