package org.shop.controller;

import org.shop.dao.entity.OrderItem;
import org.shop.dao.repository.OrderItemRepository;
import org.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/orders/{orderId}/orderItems")
    public ResponseEntity<List<OrderItem>> getAllOrderItemsByOrder(@PathVariable Long orderId){
        List<OrderItem> orderItems = orderItemService.findAllOrderItemsByOrder(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @PostMapping("/orders/{orderId}/orderItems")
    public ResponseEntity<OrderItem> createNewOrderItem(@PathVariable Long orderId,
                                                        @Valid @RequestBody OrderItem orderItem){
        OrderItem orderItemForDB = orderItemService.saveOrderItems(orderId, orderItem);
        return new ResponseEntity<>(orderItemForDB, HttpStatus.OK);
    }

    @PutMapping("/orderItems/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long orderItemId,
                                                     @Valid @RequestBody OrderItem orderItem){
        OrderItem orderItemFromDB = orderItemService.updateOrderItem(orderItemId, orderItem);
        return new ResponseEntity<>(orderItemFromDB, HttpStatus.OK);
    }

    @DeleteMapping("/orderItems/{orderItemId}")
    public void deleteOrderItem(@PathVariable Long orderItemId){
        orderItemRepository.deleteById(orderItemId);
    }
}
