package com.geekbrains.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(schema = "spring_db", name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
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

    public Product(Long id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product() {
    }
}
