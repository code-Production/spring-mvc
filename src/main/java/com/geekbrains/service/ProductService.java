package com.geekbrains.service;

import com.geekbrains.model.Product;
import com.geekbrains.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void addProduct(String title, Double price) {
        productRepository.save(new Product(null, title, price));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductByPrice(Double price) {
        return productRepository.findProductByPrice(price);
    }

    public List<Product> getProductsByPriceAndSort(Double price) {
        Sort sort = Sort.sort(Product.class).by(Product::getTitle).ascending();
        return productRepository.findProductsByPrice(price, sort);
    }

    public List<Product> getProductsWithinLimits(Double min, Double max) {
        if (min == null) {min = 0D;};
        if (max == null) {max = Double.MAX_VALUE;};
        return productRepository.findProductsWithinLimits(min, max);
    }
}
