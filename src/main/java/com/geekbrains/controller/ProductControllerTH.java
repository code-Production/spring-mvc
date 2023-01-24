package com.geekbrains.controller;

import com.geekbrains.mapper.ProductMapper;
import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDto;
import com.geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductControllerTH {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductPage(Model model) {
        List<Product> productList = productService.getFilteredProducts(null , null, null).getContent();
        model.addAttribute("newProduct", new ProductDto());
        model.addAttribute("oldProduct", new ProductDto());
        model.addAttribute("productList", productList);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toEntity(productDto);
        productService.addProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String editProduct(ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toEntity(productDto);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/update_form/{id}")
    public String showProductPageAndFillForm(@PathVariable Long id, Model model) {
        List<Product> productList = productService.getFilteredProducts(null , null, null).getContent();
        Product managedProduct = productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("newProduct", new ProductDto());
        model.addAttribute("oldProduct", ProductMapper.MAPPER.toDto(managedProduct));
        model.addAttribute("productList", productList);
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }



//    @GetMapping("/users")
//    public String getUserList(Model model) {
//
//    }

}
