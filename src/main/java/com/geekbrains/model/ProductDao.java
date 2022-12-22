package com.geekbrains.model;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(Long id);
    void deleteById(Long id);
    Product saveOrUpdate(Product product);
}
