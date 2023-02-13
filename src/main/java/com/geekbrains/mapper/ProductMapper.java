package com.geekbrains.mapper;

import com.geekbrains.model.Product;
import com.geekbrains.model.ProductDto;
import com.geekbrains.soap.product.ProductSoap;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    ProductSoap toSoap(Product product);
}
