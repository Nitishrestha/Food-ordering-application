package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import com.foodorderingapp.service.UserService;
import com.foodorderingapp.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {


    private final OrderUtil orderUtil;
    private final UserService userService;
    private final FoodService foodService;
    private final OrderDAO orderDAO;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersServiceImpl(UserService userService, OrderUtil orderUtil,
                             FoodService foodService, OrderDAO orderDAO,
                             OrderDetailService orderDetailService) {
        this.userService = userService;
        this.foodService = foodService;
        this.orderDAO = orderDAO;
        this.orderDetailService = orderDetailService;
        this.orderUtil = orderUtil;
    }

    double balance;
    public BillDto add(OrderDto orderDto) {
        BillDto bal = new BillDto();
        List<Food> foodList = new ArrayList<>();
        User user = userService.getUser(orderDto.getUserId());
        Orders orders=new Orders();
        orders.setUser(user);

        for (FoodQuantity foodQuantity : orderDto.getFoodList()) {
                OrderDetail orderDetail1 = orderDetailService
                        .getOrderDetailByUserId(orderDto.getUserId(),
                                foodQuantity.getFoodName(), foodQuantity.getRestaurantName());
                if (orderDetail1 != null) {
                    int quantity = foodQuantity.getQuantity() + orderDetail1.getQuantity();
                    orderDetail1.setQuantity(quantity);
                    orderDetailService.updateOrderDetail(orderDetail1);
                } else {
                        Orders order = orderDAO.getOrderByUserWithConfirm(orderDto.getUserId());
                        OrderDetail orderDetail = new OrderDetail();
                        if(order!=null) {
                            orderDetail.setOrders(order);
                        }else {
                            orderDAO.add(orders);
                            orderDetail.setOrders(orders);
                        }
                        orderDetail.setQuantity(foodQuantity.getQuantity());
                        orderDetail.setFoodName(foodQuantity.getFoodName());
                        orderDetail.setRestaurantName(foodQuantity.getRestaurantName());
                        orderDetail.setFoodPrice(foodQuantity.getFoodPrice());
                        orderDetailService.add(orderDetail);
                    }
                Food food = foodService.getFoodByResName(foodQuantity.getRestaurantName(), foodQuantity.getFoodName());
                foodList.add(food);
                double amount = foodQuantity.getQuantity() * foodQuantity.getFoodPrice();
                balance = user.getBalance() - amount;
                user.setBalance(balance);
                userService.update(user);
                bal.setBalance(balance);
                bal.setFoodList(foodList);
        }
        return bal;
    }

    public List<OrderListDto> getOrderForAdminForMonth() {

        try {
            List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderForAdminForMonth();
            List<OrderListDto> orderListDtoList = new ArrayList<>();
            for (OrderListMapperDto orderListMapperDto : orderListMapperDtoList) {
                OrderListDto orderListDto= orderUtil.getOrderListMapperDtoList(orderListMapperDto);
                orderListDtoList.add(orderListDto);
            }
            return  orderListDtoList;
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find order list" + e.getMessage());
        }
    }

    public List<OrderListDto> getOrderLogForAdminForToday() {

        try {
            List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderLogForAdminForToday();
            List<OrderListDto> orderListDtoList = new ArrayList<>();
            for (OrderListMapperDto orderListMapperDto : orderListMapperDtoList) {
                OrderListDto orderListDto= orderUtil.getOrderListMapperDtoList(orderListMapperDto);
                orderListDtoList.add(orderListDto);
            }
            return orderListDtoList;
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find order list" + e.getStackTrace());
        }
    }


    public List<UserListDto> getUsersByUserForAMonth(int userId) {
        try {
            List<UserListMapperDto> userListMapperDtos = userService.getUsersByUserForAMonth(userId);
            List<UserListDto> userListDtoList = new ArrayList<>();
            for (UserListMapperDto userListMapperDto : userListMapperDtos) {
                UserListDto userListDto=orderUtil.getUserListDto(userListMapperDto);
                userListDtoList.add(userListDto);
            }
            return userListDtoList;
        }
        catch (Exception e) {
            throw new DataNotFoundException("Cannot find order list"+e.getMessage());
        }
    }

    public List<UserListDto> getUsersByUserForToday(int userId) {
        try {
            List<UserListMapperDto> userListMapperDtos = userService.getByUserForToday(userId);
            List<UserListDto> userListDtoList = new ArrayList<>();

            for (UserListMapperDto userListMapperDto : userListMapperDtos) {
                UserListDto userListDto=orderUtil.getUserListDto(userListMapperDto);
                userListDtoList.add(userListDto);
            }
            return userListDtoList;
        }
        catch (Exception e) {
            throw new DataNotFoundException("Cannot find order list"+e.getMessage());
        }
    }

    public Orders updateConfirm(int orderId) {
        Orders orders1 = orderDAO.getOrder(orderId);
        if (orders1 == null) {
            throw new DataNotFoundException("cannot find order.");
        }
        orders1.setConfirm(true);
        orderDAO.update(orders1);
        return orders1;
    }

    public Orders updateWatched(int orderId) {
        Orders orders1 = orderDAO.getOrder(orderId);
        if (orders1 == null) {
            throw new DataNotFoundException("cannot find order.");
        }
        orders1.setWatched(true);
        orderDAO.update(orders1);
        return orders1;
    }
}

