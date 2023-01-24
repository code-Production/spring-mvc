package com.geekbrains.service;

import com.geekbrains.mapper.ProductMapper;
import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDto;
import com.geekbrains.model.ProductPosition;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
@Getter
public class CartService {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private List<ProductPosition> list;

    @PostConstruct
    public void init() {
        this.list = new ArrayList<>();
    }

    public List<ProductPosition> addProductPosition(Long productId, Integer amount) {

        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean found = false;
        if (amount == null) { amount = 1;}

        if (!list.isEmpty()) {
            for (ProductPosition position : list) {
                if (position.getProductDto().getId().equals(productId)) {
                    position.setAmount(position.getAmount() + amount);
                    position.setSum(position.getProductDto().getPrice() * position.getAmount());
                    found = true;
                    break;
                }
            }
        }
        if (list.isEmpty() || !found) {
            ProductDto productDto = ProductMapper.MAPPER.toDto(product);
            list.add(new ProductPosition(productDto, amount, product.getPrice()));
        }
        
        return list;
    }

    public List<ProductPosition> removeProductPosition(Long productId, Integer amount) {
        if (!list.isEmpty()) {
            for (ProductPosition position : list) {
                if (position.getProductDto().getId().equals(productId)) {
                    if (amount == null || position.getAmount().equals(amount)) {
                        list.remove(position);
                    } else {
                        position.setAmount(position.getAmount() - amount);
                        position.setSum(position.getProductDto().getPrice() * position.getAmount());
                    }
                    return list;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
