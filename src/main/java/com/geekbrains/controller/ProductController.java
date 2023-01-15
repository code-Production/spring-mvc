package com.geekbrains.controller;

import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDto;
import com.geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<ProductDto> getFilteredProducts(
            @RequestParam(required = false, name = "min_price") Double minPrice,
            @RequestParam(required = false, name = "max_price") Double maxPrice,
            @RequestParam(required = false, name = "page_num") Integer pageNum
    ){
        return productService.getFilteredProducts(minPrice, maxPrice, pageNum).map(ProductDto::new);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(productService.addProduct(new Product(productDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(productService.updateProduct(new Product(productDto)));
    }

}
