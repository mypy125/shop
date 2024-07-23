package com.daniam.shop.service;

import com.daniam.shop.controller.dto.ProductCreateRequestDto;
import com.daniam.shop.domain.Product;
import com.daniam.shop.controller.dto.ProductUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductCreateRequestDto dto);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductUpdateRequestDto request);
    void deleteProduct(Long id);
}
