package com.geekbrains.model;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String title;
    private Double price;

    public Product(Long id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product() {
    }
}
