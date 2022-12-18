package com.geekbrains.controller;

import com.geekbrains.model.Product;
//import com.geekbrains.repository.ProductRepository;
import com.geekbrains.model.ProductDao;
import com.geekbrains.repository.ProductRepository;
import com.geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/show_all")
    @ResponseBody
    public List<Product> showAllProducts() {
        return  productService.getAllProducts();
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String title, @RequestParam double price) {
        productService.addProduct(title, price);
        return "redirect:/index.html";
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/show/{id}")
    @ResponseBody
    public Product showProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

}
