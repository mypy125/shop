package com.daniam.service;

import com.daniam.controller.dto.ProductCreateRequestDto;
import com.daniam.controller.dto.ProductUpdateRequestDto;
import com.daniam.domain.Product;
import com.daniam.exception.ProductException;
import com.daniam.exception.ProductNotFoundException;
import com.daniam.mapping.ProductMapping;
import com.daniam.repository.ProductRepository;
import com.daniam.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapping productMapping;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createProduct_WhenProductCodeExists() {
        ProductCreateRequestDto dto = new ProductCreateRequestDto();
        dto.setCode("code1");

        when(productRepository.existsByCode("code1")).thenReturn(true);

        assertThrows(ProductException.class, () -> productService.createProduct(dto));
    }

    @Test
    void createProduct_WhenProductCodeDoesNotExist() {
        ProductCreateRequestDto dto = new ProductCreateRequestDto();
        dto.setCode("code1");
        Product product = new Product();
        product.setCode("code1");

        when(productRepository.existsByCode("code1")).thenReturn(false);
        when(productMapping.toEntity(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(dto);

        assertNotNull(createdProduct);
        assertEquals("code1", createdProduct.getCode());
    }

    @Test
    void getProductById_WhenProductExists() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(1L, foundProduct.get().getId());
    }

    @Test
    void getProductById_WhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertFalse(foundProduct.isPresent());
    }

    @Test
    void updateProduct_WhenProductExists() {
        Product product = new Product();
        product.setId(1L);
        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto();
        requestDto.setCode("newCode");
        requestDto.setPrice(BigDecimal.valueOf(100));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1L, requestDto);

        assertEquals("newCode", updatedProduct.getCode());
        assertEquals(BigDecimal.valueOf(100), updatedProduct.getPrice());
    }

    @Test
    void deleteProduct_WhenProductExists() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void deleteProduct_WhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1L));
    }

}