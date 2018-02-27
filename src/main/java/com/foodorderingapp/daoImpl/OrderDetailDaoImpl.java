package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.model.OrderDetail;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrderDetailDAO")
public class OrderDetailDaoImpl implements OrderDetailDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDetailDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public OrderDetail add(OrderDetail orderDetail) {
        try {
            sessionFactory.getCurrentSession().save(orderDetail);
            return orderDetail;
        } catch (Exception ex) {
            throw new BadRequestException("cannot add orderDetail");
        }
    }

    public List<OrderDetailDto> getOrderDetail() {

      Query qry=sessionFactory.getCurrentSession().createNativeQuery(" SELECT tbl_users.first_name,tbl_users.middle_name,tbl_order_detail.food_price,tbl_users.last_name," +
              "tbl_order_detail.restaurant_name,tbl_orders.ordered_date,tbl_orders.order_id, tbl_order_detail.food_name ,tbl_order_detail.quantity " +
              "FROM ((tbl_order_detail INNER join tbl_orders " +
              "ON tbl_orders.order_id = tbl_order_detail.order_id) INNER " +
              "JOIN tbl_users on tbl_users.user_id = tbl_orders.user_id)","OrderDetailMapping");
      return qry.getResultList();
    }

    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        Query query= sessionFactory.getCurrentSession().createQuery("FROM OrderDetail where orders.orderId=:orderId",OrderDetail.class).
                setParameter("orderId",orderId);
      return  query.getResultList();
    }
}
