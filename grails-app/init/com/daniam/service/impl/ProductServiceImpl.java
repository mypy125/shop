package com.daniam.service.impl;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;
import com.daniam.exception.ProductException;
import com.daniam.exception.ProductNotFoundException;
import com.daniam.mapping.ProductMapping;
import com.daniam.repository.ProductRepository;
import com.daniam.request.ProductUpdateRequestDto;
import com.daniam.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapping productMapping;

    @Override
    public Product createProduct(ProductCreateDto dto) {
        if(productRepository.existsByCode(dto.getCode())){
            throw new ProductException("Product with this code already exists");
        }
        Product createProduct = productMapping.toEntity(dto);
        return productRepository.save(createProduct);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.of(productRepository.findById(id).get());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateRequestDto request) {
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        updateProduct.setCode(request.getCode());
        updateProduct.setPrice(request.getPrice());
        updateProduct.setProductionDate(request.getProductionDate());
        updateProduct.setExpirationDate(request.getExpirationDate());
        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product deletProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        productRepository.delete(deletProduct);
    }

}
