package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory ){
        this.sessionFactory=sessionFactory;
    }

    public Orders getOrderByUserWithConfirm(int userId) {
       try {
           return sessionFactory.getCurrentSession()
                   .createQuery("from Orders o where o.user.userId=:userId and o.confirm is false", Orders.class)
                   .setParameter("userId", userId).getSingleResult();
       } catch (Exception e) {
            return null;
       }
   }

    public Orders add(Orders orders) {
        try {
            sessionFactory.getCurrentSession().save(orders);
            return  orders;
        } catch (Exception e) {
            throw new BadRequestException("cannnot add order");
        }
    }

    public List<OrderListMapperDto> getOrderForAdminForMonth() {
        Query qry=sessionFactory
                .getCurrentSession()
                .createNativeQuery("select tbl_orders.order_id,tbl_orders.ordered_date ,\n" +
                        "tbl_orders.user_id ,tbl_users.first_name,tbl_orders.confirm,tbl_users.middle_name,tbl_users.last_name\n" +
                        "from tbl_orders INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id WHERE YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date)\n" +
                        "AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date)","OrderMapping");
        return qry.getResultList();
    }

    @Override
    public List<OrderListMapperDto> getOrderLogForAdminForToday() {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT   tbl_orders.order_id,tbl_orders.ordered_date ,\n" +
                        "tbl_orders.user_id ,tbl_users.first_name,tbl_orders.confirm," +
                        "tbl_users.middle_name,tbl_users.last_name" +
                        " FROM tbl_orders  INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id\n" +
                        "WHERE CAST(tbl_orders.ordered_date AS DATE)=CURRENT_DATE\n" +
                        "AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC","OrderMapping");
        List<OrderListMapperDto> orderListMapperDtoList=qry.getResultList();
        return orderListMapperDtoList;
    }


    public Boolean update(Orders orders) {
        try {
            sessionFactory.getCurrentSession().update(orders);
            return true;
        } catch (Exception ex) {
            throw  new UserConflictException("cannot update order.");
        }
    }

    public Orders getOrder(int orderId) {
        try {
            Orders orders = sessionFactory.getCurrentSession().get(Orders.class, orderId);
            return orders;
        } catch (Exception ex) {
            throw new DataNotFoundException("cannot find order");
        }
    }

/*
    @Override
    public List<OrderListMapperDto> getOrderLogForAdminForAMonth(PageModel pageModel) {
        Query qry=sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_users.first_name,tbl_users.middle_name,tbl_users.last_name,tbl_order_detail.food_name,\n" +
                        "tbl_order_detail.quantity,tbl_order_detail.food_price,tbl_order_detail.restaurant_name,\n" +
                        "tbl_orders.ordered_date from(( tbl_order_detail\n" +
                        "INNER JOIN tbl_orders ON tbl_orders.order_id=tbl_order_detail.order_id)\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id)\n" +
                        "WHERE  YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date) \n" +
                        "AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date)\n" +
                        "AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC","OrderMapping");
        qry.setFirstResult(pageModel.getFirstResult()*pageModel.getMaxResult());
        qry.setMaxResults(pageModel.getMaxResult());
        List<OrderListMapperDto> orderListMapperDtoList=qry.getResultList();
        return orderListMapperDtoList;

    }
*/



    public Long countOrder() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select count(1) from  OrderDetail",Long.class)
                .getSingleResult();
    }
}
