package org.shop.model.input;

import lombok.Getter;
import lombok.Setter;
import org.shop.dao.entity.Order;

import java.util.Date;

@Getter
@Setter
public class OrderInputModel {

    private Date createdAt;
    private Long userId;
    private String comment;

    public static Order of(OrderInputModel orderInputModel){
        return new Order(
                orderInputModel.getCreatedAt(),
                orderInputModel.getComment()
        );
    }
}
