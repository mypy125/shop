package com.daniam.mapping;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;

public class ProductMapping implements Mapping<Product, ProductCreateDto> {
    @Override
    public ProductCreateDto toDto(Product entity) {
        return ProductCreateDto.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .price(entity.getPrice())
                .productionDate(entity.getProductionDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }

    @Override
    public Product toEntity(ProductCreateDto dto) {
        return Product.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .price(dto.getPrice())
                .productionDate(dto.getProductionDate())
                .expirationDate(dto.getExpirationDate())
                .build();
    }
}
