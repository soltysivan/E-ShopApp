package org.shop.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 45)
    @Size(max = 45)
    private String name;

    @Column(length = 255)
    @Size(max = 255)
    private String description;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public Category(Long id, String name, String  description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(String name, String  description) {
        this.name = name;
        this.description = description;
    }
}
