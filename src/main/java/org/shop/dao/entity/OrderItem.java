package org.shop.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public OrderItem(int quantity) {
        this.quantity = quantity;
    }
}
