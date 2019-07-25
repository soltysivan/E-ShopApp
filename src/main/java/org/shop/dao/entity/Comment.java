package org.shop.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    @Size(max = 255)
    private String text;

    private LocalDateTime creationDate;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private User authorComments;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Product product;

}
