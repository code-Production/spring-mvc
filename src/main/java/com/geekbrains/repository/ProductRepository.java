package com.geekbrains.repository;

import com.geekbrains.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> productList;
    private static long counter = 1L;

    public ProductRepository() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(counter++, "Milk", 80.0),
                new Product(counter++, "Bread", 60.0),
                new Product(counter++, "Eggs", 40.0),
                new Product(counter++, "Butter", 20.0),
                new Product(counter++, "Meat", 160.0)
        ));
    }

    public void addProduct(Product product) {
        product.setId(counter++);
        productList.add(product);
    }

    public Product getProductById(Long id) {
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }

    public void deleteProduct(Long id) {
        productList.removeIf(product -> product.getId().equals(id));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void changeProductPosition(Long id, Integer delta) {
        int position = 0;
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                System.out.println("pos:" + position);
                if (position + delta >= 0 && position + delta < productList.size()) {
                    Collections.swap(productList, position, position + delta);
                }
                break;
            }
            position++;
        }
    }

}

