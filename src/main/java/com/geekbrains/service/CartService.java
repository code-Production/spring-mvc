package com.geekbrains.service;

import com.geekbrains.mapper.ProductMapper;
import com.geekbrains.model.*;
import com.geekbrains.repository.OrderItemRepository;
import com.geekbrains.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class CartService {

    private ProductService productService;

    private UserService userService;

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

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

    @Transactional
    public void makeOrder() {

        if (!list.isEmpty()) {
            User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            double sum = 0D;
            Order order = new Order(null, user, null, null, 0D, null);
            order = orderRepository.save(order);
            for (ProductPosition productPosition : list) {
                Product product = ProductMapper.MAPPER.toEntity(productPosition.getProductDto());
                OrderItem item = new OrderItem(null, order, product, product.getPrice(), productPosition.getAmount());
                sum += (product.getPrice() * productPosition.getAmount());
                orderItemRepository.save(item);
            }
            order.setTotal(sum);
            list.clear();
        }
    }


}
