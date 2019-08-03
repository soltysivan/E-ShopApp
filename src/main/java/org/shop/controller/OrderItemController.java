package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.OrderItem;
import org.shop.dao.repository.OrderItemRepository;
import org.shop.model.input.OrderItemInputModel;
import org.shop.model.output.OrderItemOutputModel;
import org.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop/order-items")
@Api(value = "OrderItem", description = "Order Items", basePath = "/api/shop/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation(value = "Get all order-items")
    @GetMapping
    public ResponseEntity<List<OrderItemOutputModel>> getAllOrderItems(){
        List<OrderItemOutputModel> orderItemOutputModels = orderItemService.findAll();
        return new ResponseEntity<>(orderItemOutputModels, HttpStatus.OK);
    }

    @ApiOperation(value = "Get order-items by param id")
    @GetMapping("{orderItemId}")
    public ResponseEntity<OrderItemOutputModel> getOrderItemById(
            @ApiParam(value = "OrderItem param id", example = "1")@PathVariable Long orderItemId){
        OrderItemOutputModel orderItemOutputModel = orderItemService.findById(orderItemId);
        return new ResponseEntity<>(orderItemOutputModel, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all orders by user param id")
    @GetMapping("orders/{orderId}")
    public ResponseEntity<List<OrderItem>> getAllOrderItemsByOrder(
            @ApiParam(value = "Order param id", example = "1")@PathVariable Long orderId){
        List<OrderItem> orderItems = orderItemService.findAllOrderItemsByOrder(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @ApiOperation(value = "Create new order-item")
    @PostMapping
    public ResponseEntity<OrderItemOutputModel> createNewOrderItem(
            @ApiParam(value = "Request body OrderItem", required = true)@Valid @RequestBody OrderItemInputModel orderItemInputModel){
        OrderItemOutputModel orderItemOutputModel = orderItemService.saveOrderItems(orderItemInputModel);
        return new ResponseEntity<>(orderItemOutputModel, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update order-item")
    @PutMapping("{orderItemId}")
    public ResponseEntity<OrderItemOutputModel> updateOrderItem(
            @ApiParam(value = "Order-item param id", example = "1")@PathVariable Long orderItemId,
            @ApiParam(value = "Request body OrderItem", required = true)@Valid @RequestBody OrderItem orderItem){
        OrderItemOutputModel orderItemOutputModel = orderItemService.updateOrderItem(orderItemId, orderItem);
        return new ResponseEntity<>(orderItemOutputModel, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete order-item")
    @DeleteMapping("{orderItemId}")
    public void deleteOrderItem(
            @ApiParam(value = "Order-item param id", example = "1")@PathVariable Long orderItemId){
        orderItemRepository.deleteById(orderItemId);
    }
}
