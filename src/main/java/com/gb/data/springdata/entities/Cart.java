package com.gb.data.springdata.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@Data
public class Cart {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_carts",
            joinColumns =  @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
