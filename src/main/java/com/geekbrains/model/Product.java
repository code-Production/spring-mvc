package com.geekbrains.model;

//import javax.persistence.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@ToString(exclude = "orders")

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

    public Product(Long id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
