package org.shop.model.input;

import lombok.Getter;
import org.shop.dao.entity.OrderItem;

@Getter
public class OrderItemInputModel {

    private int quantity;
    private Long orderId;
    private Long productId;

    public static OrderItem of(OrderItemInputModel orderItemInputModel){
        return new OrderItem(
          orderItemInputModel.getQuantity()
        );
    }
}
