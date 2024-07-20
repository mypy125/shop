package com.daniam;

import com.daniam.dto.ProductCreateRequestDto;
import com.daniam.Product;
import com.daniam.dto.ProductUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductCreateRequestDto dto);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductUpdateRequestDto request);
    void deleteProduct(Long id);
}
