package com.geekbrains.service;

import com.geekbrains.model.Product;
import com.geekbrains.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProductList();
    }

    public void addProduct(String title, Double price) {
        productRepository.addProduct(new Product(0L, title, price));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    public void changeProductPosition(Long id, Integer delta) {
        productRepository.changeProductPosition(id, delta);
    }
}
