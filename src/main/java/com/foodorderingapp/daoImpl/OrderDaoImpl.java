package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDAO{

    private final SessionFactory sessionFactory;
    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory ,OrderDetailDAO orderDetailDAO){
        this.sessionFactory=sessionFactory;
        this.orderDetailDAO=orderDetailDAO;
    }

   public List<OrderDetail> getOrderDetailByUserForToday(int userId){
       Calendar cal = Calendar.getInstance();
       cal.setTime(new Date());

      Query qry= sessionFactory.getCurrentSession()
                .createQuery("  from OrderDetail od where odx   x   .orders.user.userId=:userId",OrderDetail.class)
              .setParameter("userId",userId);

       List<OrderDetail> orderDetailList=qry.getResultList();
      return  orderDetailList;
    }
    public Orders add(Orders orders) {
        try {
            sessionFactory.getCurrentSession().save(orders);
            return  orders;
        } catch (Exception e) {
            throw new BadRequestException("cannnot add order");
        }
    }


    public List<OrderListMapperDto> getOrders() {
        Query qry=sessionFactory
                .getCurrentSession()
                .createNativeQuery("select tbl_orders.order_id,tbl_orders.ordered_date ," +
                        " tbl_orders.user_id from tbl_orders WHERE YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date)" +
                        " AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date)","OrderMapping");
        return qry.getResultList();
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

    @Override
    public List<OrderListMapperDto> getOrderLogForAdminForAMonth(PageModel pageModel) {
        Query qry=sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_users.first_name,tbl_users.last_name,\n" +
                        "tbl_order_detail.food_name,tbl_order_detail.food_price,tbl_order_detail.restaurant_name," +
                        " tbl_orders.ordered_date from(( tbl_order_detail \n" +
                        "INNER JOIN tbl_orders ON tbl_orders.order_id=tbl_order_detail.order_id)\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id)" +
                        "WHERE YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date) " +
                        "AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date) \n" +
                        " AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC","OrderMapping");
        qry.setFirstResult(pageModel.getFirstResult()*pageModel.getMaxResult());
        qry.setMaxResults(pageModel.getMaxResult());
        List<OrderListMapperDto> orderListMapperDtoList=qry.getResultList();
        return orderListMapperDtoList;

    }

    @Override
    public List<OrderListMapperDto> getOrderLogForAdminForToday() {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_users.first_name,tbl_users.last_name,\n" +
                        "tbl_order_detail.food_name,tbl_order_detail.food_price,tbl_order_detail.restaurant_name," +
                        " tbl_orders.ordered_date from(( tbl_order_detail \n" +
                        "INNER JOIN tbl_orders ON tbl_orders.order_id=tbl_order_detail.order_id)\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id)" +
                        "WHERE CAST(tbl_orders.ordered_date AS DATE)=CURRENT_DATE\n" +
                        " AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC","OrderMapping");
        List<OrderListMapperDto> orderListMapperDtoList=qry.getResultList();
        return orderListMapperDtoList;
    }


    public Long countOrder() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select count(1) from  OrderDetail",Long.class)
                .getSingleResult();
    }
}
