package org.shop.service;

import org.shop.dao.entity.Order;
import org.shop.dao.entity.OrderItem;
import org.shop.dao.repository.OrderItemRepository;
import org.shop.dao.repository.OrderRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;


    public List<OrderItem> findAllOrderItemsByOrder(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findAll()
                .stream().filter(orderItem -> orderItem.getOrder().getId().equals(orderId))
                .collect(Collectors.toList());
        return orderItems;
    }

    public OrderItem saveOrderItems(Long orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(NotFoundExceptions::new);
        orderItem.setOrder(order);
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItem) {
        OrderItem orderItemFromDB = orderItemRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        BeanUtils.copyProperties(orderItem, orderItemFromDB, "id", "order");
        return orderItemRepository.save(orderItemFromDB);
    }
}
