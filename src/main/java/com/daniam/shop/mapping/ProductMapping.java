package com.daniam.shop.mapping;

import com.daniam.shop.controller.dto.ProductCreateRequestDto;
import com.daniam.shop.domain.Product;
import org.springframework.stereotype.Service;

@Service
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
