package org.shop.model.output;

import lombok.Getter;
import lombok.Setter;
import org.shop.dao.entity.OrderItem;

@Getter
public class OrderItemOutputModel {
    private final Long id;
    private final int quantity;
    private final Long orderId;
    private final Long productId;

    public OrderItemOutputModel(Long id, int quantity, Long orderId, Long productId) {
        this.id = id;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public static OrderItemOutputModel of(OrderItem orderItem){
        return new OrderItemOutputModel(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getOrder().getId(),
                orderItem.getProduct().getId()
        );
    }
}
