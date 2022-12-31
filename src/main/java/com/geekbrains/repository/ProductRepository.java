package com.geekbrains.repository;

import com.geekbrains.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {
    Optional<Product> findProductByPrice(Double price);
    List<Product> findProductsByPrice(Double price, Sort sort);

    @Query("SELECT p FROM Product AS p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findProductsWithinLimits(Double min, Double max);

}
