package com.daniam.service.impl;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;
import com.daniam.mapping.ProductMapping;
import com.daniam.repository.ProductRepository;
import com.daniam.service.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapping productMapping;

    @Override
    public Product createProduct(ProductCreateDto dto) {
        Product createProduct = productMapping.toEntity(dto);
        return productRepository.save(createProduct);
    }
}
