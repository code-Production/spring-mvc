package com.geekbrains.service;

import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDao;
import com.geekbrains.repository.ProductRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }


    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public void addProduct(String title, Double price) {
        productDao.saveOrUpdate(new Product(0L, title, price));
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

    public Product getProduct(Long id) {
        return productDao.findById(id);
    }

}
