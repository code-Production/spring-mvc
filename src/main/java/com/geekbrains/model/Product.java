package com.geekbrains.model;

//import javax.persistence.*;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
//@ToString(exclude = "orders")

@Entity
@Table(schema = "spring_db", name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product AS p WHERE p.id = :id"),
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product AS p")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

//    @OneToMany(mappedBy = "product")
//    @Transient
//    private List<Order> orders;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Product(Long id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(ProductDto productDto) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.price = productDto.getPrice();
        this.createdAt = LocalDateTime.now();
    }

}
