package org.shop.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Data
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String name;

    private String text;

    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Product product;

    public Article(String name, String text, Date createdAt) {
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
    }
}
