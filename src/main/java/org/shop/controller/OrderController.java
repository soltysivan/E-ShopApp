package org.shop.controller;

import org.shop.dao.entity.Order;
import org.shop.dao.repository.OrderRepository;
import org.shop.dao.repository.UserRepository;
import org.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<Order>> getAllUserOrders(@PathVariable Long userId){
        List<Order> orders = orderService.findAllOrdersByUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<Order> createNewOrder(@PathVariable Long userId,
                                                @Valid @RequestBody Order order){
        Order orderForDB = orderService.saveOrder(userId, order);
        return new ResponseEntity<>(orderForDB, HttpStatus.OK);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId,
                                             @Valid @RequestBody Order order){
        Order orderForDb = orderService.updateOrder(orderId, order);
        return new ResponseEntity<>(orderForDb, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderRepository.deleteById(orderId);
    }
}
