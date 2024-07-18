package com.daniam.mapping;

import com.daniam.controller.dto.ProductCreateDto;
import com.daniam.domain.Product;

import java.time.LocalDate;

public class ProductMapping implements Mapping<Product, ProductCreateDto> {
    @Override
    public ProductCreateDto toDto(Product entity) {
        return new ProductCreateDto(
                entity.getCode(),
                entity.getName(),
                entity.getPrice(),
                entity.getProductionDate(),
                entity.getExpirationDate()
        );
    }

    @Override
    public Product toEntity(ProductCreateDto dto) {
        return new Product(
                dto.getCode(),
                dto.getName(),
                dto.getPrice(),
                dto.getProductionDate(),
                dto.getExpirationDate()
        );
    }
}
