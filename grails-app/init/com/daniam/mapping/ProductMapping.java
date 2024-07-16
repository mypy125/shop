package com.daniam.mapping;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;

public class ProductMapping implements Mapping<Product, ProductCreateDto> {
    @Override
    public ProductCreateDto toDto(Product entity) {
        return new ProductCreateDto(
                entity.getCode(),
                entity.getName(),
                entity.getPrice(),
                entity.getProductionDate(),
                entity.getExpirationDate(),
                entity.getQuantity());
    }

    @Override
    public Product toEntity(ProductCreateDto dto) {
        return Product.builder()
                .code(dto.code())
                .name(dto.name())
                .price(dto.price())
                .productionDate(dto.productionDate())
                .expirationDate(dto.expirationDate())
                .quantity(dto.quantity())
                .build();
    }
}
