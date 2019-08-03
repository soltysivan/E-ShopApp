package org.shop.service;

import org.shop.dao.entity.Order;
import org.shop.dao.entity.OrderItem;
import org.shop.dao.entity.Product;
import org.shop.dao.repository.OrderItemRepository;
import org.shop.dao.repository.OrderRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.OrderItemInputModel;
import org.shop.model.output.OrderItemOutputModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired private OrderItemRepository orderItemRepository;

    @Autowired private OrderRepository orderRepository;

    @Autowired private ProductRepository productRepository;


    public List<OrderItem> findAllOrderItemsByOrder(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findAll()
                .stream().filter(orderItem -> orderItem.getOrder().getId().equals(orderId))
                .collect(Collectors.toList());
        return orderItems;
    }

    public OrderItemOutputModel saveOrderItems(OrderItemInputModel orderItemInputModel) {
        Order order = orderRepository.findById(orderItemInputModel.getOrderId())
                .orElseThrow(NotFoundExceptions::new);
        Product product = productRepository.findById(orderItemInputModel.getProductId())
                .orElseThrow(NotFoundExceptions::new);
        OrderItem orderItem = OrderItemInputModel.of(orderItemInputModel);
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItemRepository.save(orderItem);
        return OrderItemOutputModel.of(orderItem);
    }

    public OrderItemOutputModel updateOrderItem(Long id, OrderItem orderItem) {
        OrderItem saved = orderItemRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        saved.setQuantity(orderItem.getQuantity());
        saved.setProduct(orderItem.getProduct());
        orderItemRepository.save(saved);
        return OrderItemOutputModel.of(orderItem);
    }

    public List<OrderItemOutputModel> findAll() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItemOutputModel> orderItemOutputModels = new ArrayList<>();
        orderItems.forEach(orderItem -> orderItemOutputModels.add(OrderItemOutputModel.of(orderItem)));
        return orderItemOutputModels;
    }

    public OrderItemOutputModel findById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(NotFoundExceptions::new);
        return OrderItemOutputModel.of(orderItem);
    }
}
