package com.geekbrains.service;

import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDto;
import com.geekbrains.repository.ProductRepository;
import com.geekbrains.repository.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static final int PAGE_SIZE = 5;

    private ProductRepository productRepository;

    @Autowired
    public void setProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        product.setId(0L);
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

//    @EventListener(ApplicationReadyEvent.class)

    public Page<Product> getFilteredProducts(Double minPrice, Double maxPrice, Integer pageNum) {

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualThan(maxPrice));
        }
        if (pageNum == null) {pageNum = 0;}

        Sort sort = Sort.sort(Product.class).by(Product::getTitle).descending();
//        PageRequest.of(pageNum, PAGE_SIZE, sort)
        return productRepository.findAll(spec, Pageable.unpaged());
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
