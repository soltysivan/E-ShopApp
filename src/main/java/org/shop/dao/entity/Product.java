package org.shop.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String name;

    private int price;//cop

    private int quantity;

    @Column(length = 255)
    @Size(max = 255)
    private String description;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Category category;

    private String photo;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<Comment> comments;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Article> articles;
}
