package com.geekbrains.controller;

import com.geekbrains.model.Product;
//import com.geekbrains.repository.ProductRepository;
import com.geekbrains.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> showAllProducts(@RequestParam(required = false) Double min, @RequestParam(required = false) Double max) {
        return productService.getProductsWithinLimits(min, max);
    }

    @PostMapping
    public RedirectView addNewProduct(@RequestParam String title, @RequestParam Double price) {
        productService.addProduct(title, price);
        return new RedirectView("/app/index.html");
    }

    @GetMapping("/{id}")
    public Product showProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        System.out.println("delete");
        productService.deleteProductById(id);
    }

    @GetMapping("/find")
    public Product showProductByPrice(@RequestParam Double price) {
        return productService.getProductByPrice(price)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/find/{price}")
    public List<Product> showProductsByPriceAndSort(@PathVariable Double price) {
        return productService.getProductsByPriceAndSort(price);
    }


}
