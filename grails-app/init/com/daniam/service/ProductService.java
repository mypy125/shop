package com.daniam.service;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product createProduct(ProductCreateDto dto);
}
