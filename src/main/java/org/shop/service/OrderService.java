package org.shop.service;

import org.shop.dao.entity.User;
import org.shop.dao.entity.Order;
import org.shop.dao.repository.OrderRepository;
import org.shop.dao.repository.UserRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order saveOrder(Long userId, Order order) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundExceptions::new);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order orderFromDb = orderRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        orderFromDb.setComment(order.getComment());
        orderFromDb.setOrderItems(order.getOrderItems());
        return orderRepository.save(orderFromDb);
    }

    public List<Order> findAllOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundExceptions::new);
        List<Order> orders = user.getOrders();
        return orders;
    }
}
