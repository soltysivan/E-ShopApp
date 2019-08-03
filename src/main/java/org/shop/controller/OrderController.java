package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.Order;
import org.shop.dao.repository.OrderRepository;
import org.shop.dao.repository.UserRepository;
import org.shop.model.input.OrderInputModel;
import org.shop.model.output.OrderOutputModel;
import org.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop/orders")
@Api(value = "Order", description = "Users order", basePath = "/api/shop/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Get all orders")
    @GetMapping
    private ResponseEntity<List<OrderOutputModel>> getAllOrders(){
        List<OrderOutputModel> orderOutputModels = orderService.findAll();
        return new ResponseEntity<>(orderOutputModels, HttpStatus.OK);
    }


    @ApiOperation(value = "Get all orders by User id")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderOutputModel>> getAllUserOrders(
            @ApiParam(value = "User param id", example = "1")@PathVariable Long userId){
        List<OrderOutputModel> orders = orderService.findAllOrdersByUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "Get order by id")
    @GetMapping("{orderId}")
    public ResponseEntity<OrderOutputModel> getOrderById(
            @ApiParam(value = "Order param id", example = "1")@PathVariable Long orderId){
        OrderOutputModel order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order ,HttpStatus.OK);
    }

    @ApiOperation(value = "Create new order")
    @PostMapping
    public ResponseEntity<OrderOutputModel> createNewOrder(
            @ApiParam(value = "Request body Order", required = true)@Valid @RequestBody OrderInputModel orderInputModel){
        OrderOutputModel orderOutputModel = orderService.saveOrder(orderInputModel);
        return new ResponseEntity<>(orderOutputModel, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update order")
    @PutMapping("{orderId}")
    public ResponseEntity<OrderOutputModel> updateOrder(
            @ApiParam(value = "Order param id", example = "1")@PathVariable Long orderId,
            @ApiParam(value = "Request body Order", required = true)@Valid @RequestBody Order order){
        OrderOutputModel orderForDb = orderService.updateOrder(orderId, order);
        return new ResponseEntity<>(orderForDb, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete order by id")
    @DeleteMapping("{orderId}")
    public void deleteOrder(
            @ApiParam(value = "Order param id", example = "1")@PathVariable Long orderId){
        orderRepository.deleteById(orderId);
    }
}
