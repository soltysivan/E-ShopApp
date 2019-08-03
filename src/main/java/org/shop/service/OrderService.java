package org.shop.service;

import org.shop.dao.entity.User;
import org.shop.dao.entity.Order;
import org.shop.dao.repository.OrderRepository;
import org.shop.dao.repository.UserRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.OrderInputModel;
import org.shop.model.output.OrderOutputModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderOutputModel saveOrder(OrderInputModel orderInputModel) {
        User user = userRepository.findById(orderInputModel.getUserId())
                .orElseThrow(NotFoundExceptions::new);
        Order order = OrderInputModel.of(orderInputModel);
        order.setCreatedAt(new Date());
        order.setUser(user);
        orderRepository.save(order);
        return OrderOutputModel.of(order);
    }

    public OrderOutputModel updateOrder(Long id, Order order) {
        Order orderFromDb = orderRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        orderFromDb.setComment(order.getComment());
        orderFromDb.setOrderItems(order.getOrderItems());
        orderRepository.save(orderFromDb);
        return OrderOutputModel.of(order);
    }

    public List<OrderOutputModel> findAllOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundExceptions::new);
        List<Order> orders = user.getOrders();
        List<OrderOutputModel> orderOutputModels = new ArrayList<>();
        orders.forEach(order -> orderOutputModels.add(OrderOutputModel.of(order)));
        return orderOutputModels;
    }

    public OrderOutputModel findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(NotFoundExceptions::new);
        return OrderOutputModel.of(order);
    }

    public List<OrderOutputModel> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderOutputModel> orderOutputModels = new ArrayList<>();
        orders.forEach(order -> orderOutputModels.add(OrderOutputModel.of(order)));
        return orderOutputModels;
    }
}
