package org.shop.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private byte[] password;

    private boolean active;

    @Column(name = "created_at")
    private Date createdAt;


    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;


    //User Role//
    public enum Role{
        ADMIN ("ADMIN"),
        USER ("USER");

        private final String value;

        Role(String role){
            this.value = role;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
