package com.daniam.mapping;

import com.daniam.controller.dto.ProductCreateRequestDto;
import com.daniam.domain.Product;

public class ProductMapping implements Mapping<Product, ProductCreateRequestDto> {
    @Override
    public ProductCreateRequestDto toDto(Product entity) {
        return new ProductCreateRequestDto(
                entity.getCode(),
                entity.getName(),
                entity.getPrice(),
                entity.getProductionDate(),
                entity.getExpirationDate()
        );
    }


    @Override
    public Product toEntity(ProductCreateRequestDto dto) {
        return new Product(
                dto.getCode(),
                dto.getName(),
                dto.getPrice(),
                dto.getProductionDate(),
                dto.getExpirationDate()
        );
    }

}
