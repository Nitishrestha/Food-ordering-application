package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.dto.UserLogMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @Override
    public List<UserListMapperDto> getUsersByUserForAMonth(int userId) {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_orders.order_id,tbl_orders.user_id,\n" +
                        "tbl_orders.ordered_date,tbl_orders.confirm from tbl_orders\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id\n" +
                        "WHERE YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date)\n" +
                        "AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date)\n" +
                        "AND tbl_users.user_id=? AND tbl_users.user_role=\"user\"\n" +
                        "ORDER BY tbl_orders.ordered_date DESC", "UserMapping")
                        .setParameter(1,userId);
        return qry.getResultList();

    }

    public List<UserListMapperDto> getByUserForToday(int userId) {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_orders.order_id,tbl_orders.user_id,\n" +
                        "tbl_orders.ordered_date,tbl_orders.confirm from tbl_orders\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id\n" +
                        "WHERE  CAST(tbl_orders.ordered_date AS DATE)=CURRENT_DATE \n" +
                        " AND tbl_users.user_id=? AND tbl_users.user_role=\"user\" " +
                        "ORDER BY tbl_orders.ordered_date DESC","UserMapping")
                .setParameter(1, userId);
        return qry.getResultList();
    }

  /*  @Override
    public Double getLastMonthBalanceByUserId(int userId) {
        Query query=sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_orders.user_id ,tbl_users.balance FROM tbl_orders " +
                        "INNER JOIN tbl_users ON tbl_users.user_id=tbl_orders.user_id WHERE tbl_users.user_id=?\n" +
                        "AND YEAR(ordered_date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)\n" +
                        "AND MONTH(ordered_date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)")
                .setParameter(1,userId);

    }
*/}

