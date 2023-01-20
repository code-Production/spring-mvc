package com.geekbrains.controller;

import com.geekbrains.mapper.ProductMapper;
import com.geekbrains.service.CartService;
import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDto;
import com.geekbrains.model.ProductPosition;
import com.geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.OptionalInt;

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
                .map(ProductMapper.MAPPER::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        return productService.getProductById(id)
//                .map(ProductDto::new)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<ProductDto> getFilteredProducts(
            @RequestParam(required = false, name = "min_price") Double minPrice,
            @RequestParam(required = false, name = "max_price") Double maxPrice,
            @RequestParam(required = false, name = "page_num") Integer pageNum
    ){
        return productService.getFilteredProducts(minPrice, maxPrice, pageNum).map(ProductMapper.MAPPER::toDto);
//        return productService.getFilteredProducts(minPrice, maxPrice, pageNum).map(ProductDto::new);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toEntity(productDto);
        return ProductMapper.MAPPER.toDto(productService.addProduct(product));
//        return new ProductDto(productService.addProduct(new Product(productDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toEntity(productDto);
        return ProductMapper.MAPPER.toDto(productService.updateProduct(product));
//        return new ProductDto(productService.updateProduct(new Product(productDto)));
    }


}
