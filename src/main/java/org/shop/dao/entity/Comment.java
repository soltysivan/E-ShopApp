package org.shop.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
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

    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonView()
    @ToString.Exclude
    @ManyToOne
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Product product;


    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment parent;

    public Comment(String text, Date createdAt, Date updatedAt) {
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
