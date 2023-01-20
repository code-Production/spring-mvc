package com.geekbrains.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductPosition {

    private ProductDto productDto;
    private Integer amount;
    private Double sum;

}
