package org.shop.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


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

    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Product product;

    @Column(name = "comment_id")
    private Long comment;

}
