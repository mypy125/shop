package com.daniam.service;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;
import com.daniam.request.ProductUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Product createProduct(ProductCreateDto dto);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductUpdateRequestDto request);
    void deleteProduct(Long id);
}
