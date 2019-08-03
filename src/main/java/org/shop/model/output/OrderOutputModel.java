package org.shop.model.output;

import lombok.Getter;
import org.shop.dao.entity.Order;

import java.util.Date;

@Getter
public class OrderOutputModel {

    private final Long id;
    private final Date createdAt;
    private final Long userId;
    private final String comment;

    public OrderOutputModel( Long id, Date createdAt, Long userId, String comment) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.comment = comment;
    }

    public static OrderOutputModel of(Order order){
        return new OrderOutputModel(
                order.getId(),
                order.getCreatedAt(),
                order.getUser().getId(),
                order.getComment()
        );
    }
}
