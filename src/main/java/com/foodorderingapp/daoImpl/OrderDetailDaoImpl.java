package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.utils.DateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("OrderDetailDAO")
public class OrderDetailDaoImpl implements OrderDetailDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDetailDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

        Query qry = sessionFactory.getCurrentSession().createNativeQuery(" SELECT tbl_users.first_name,tbl_users.middle_name,tbl_order_detail.food_price,tbl_users.last_name," +
                "tbl_order_detail.restaurant_name,tbl_orders.ordered_date,tbl_orders.order_id, tbl_order_detail.food_name ,tbl_order_detail.quantity " +
                "FROM ((tbl_order_detail INNER join tbl_orders " +
                "ON tbl_orders.order_id = tbl_order_detail.order_id) INNER " +
                "JOIN tbl_users on tbl_users.user_id = tbl_orders.user_id)", "OrderDetailMapping");
        return qry.getResultList();
    }

    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM OrderDetail where orders.orderId=:orderId", OrderDetail.class).
                setParameter("orderId", orderId);
        return query.getResultList();
    }


    @Override
    public Long countOrderDetail(int userId) {
        String queryString = "select count(1) from OrderDetail od where od.orders.user.userId = :userId order by od.orders.date desc ";
        return sessionFactory
                .getCurrentSession()
                .createQuery(queryString, Long.class)
                .setParameter("userId", userId).getSingleResult();
    }

    @Override
    public List<OrderDetail> getPaginatedOrderLogToUser(PageModel pageModel, int userId) {
        String queryString = "FROM OrderDetail od where od.orders.user.userId = :userId ORDER BY od.orders.date DESC";
        return sessionFactory
                .getCurrentSession()
                .createQuery(queryString, OrderDetail.class)
                .setParameter("userId", userId)
                .setFirstResult(pageModel.getFirstResult() * pageModel.getMaxResult())
                .setMaxResults(pageModel.getMaxResult())
                .getResultList();
    }

    @Override
    public List<OrderDetail> getPaginatedOrderLogToAdmin(PageModel pageModel) {
        String queryString = "FROM OrderDetail od ORDER BY od.orders.date DESC";
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(queryString, OrderDetail.class)
                .setFirstResult(pageModel.getFirstResult() * pageModel.getMaxResult())
                .setMaxResults(pageModel.getMaxResult());
        return query.getResultList();
    }

    @Override
    public Long countOrderDetail() {
        String queryString = "select count(1) from OrderDetail ";
        return sessionFactory
                .getCurrentSession()
                .createQuery(queryString, Long.class)
                .getSingleResult();
    }

    @Override
    public List<OrderDetail> getCurrentDateFoodLog(int userId) {
        String queryString = "FROM OrderDetail od where (od.orders.date between :startDate and :endDate) and od.orders.user.userId = :userId";
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(queryString, OrderDetail.class)
                .setParameter("userId", userId)
                .setParameter("startDate", DateUtil.getTodayDate())
                .setParameter("endDate", DateUtil.getTomorrowDate());
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> getPaginatedCurrentMonthFoodLog(int userId) {
        String queryString = "FROM OrderDetail od where MONTH(od.orders.date) = MONTH(:date) and od.orders.user.userId = :userId";
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(queryString, OrderDetail.class)
                .setParameter("userId", userId)
                .setParameter("date", new Date());
        List<OrderDetail> orderDetailList = query.getResultList();
        return orderDetailList;
    }
}
