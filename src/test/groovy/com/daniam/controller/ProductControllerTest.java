package com.daniam.controller;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;
import com.daniam.request.ProductUpdateRequestDto;
import com.daniam.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = Product.builder()
                .id(1L)
                .code("12345")
                .name("Test product1")
                .price(BigDecimal.valueOf(19.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .code("12345")
                .name("Test product2")
                .price(BigDecimal.valueOf(25.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        List<Product> mockProducts = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProducts, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product mockProduct = Product.builder()
                .id(productId)
                .code("12345")
                .name("Test product")
                .price(BigDecimal.valueOf(19.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        when(productService.getProductById(productId)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
        verify(productService, times(1)).getProductById(productId);

        Long nonExistentId = 100L;
        when(productService.getProductById(nonExistentId)).thenReturn(Optional.empty());

        ResponseEntity<Product> notFoundResponse = productController.getProductById(nonExistentId);

        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
        assertEquals(null, notFoundResponse.getBody());
    }

    @Test
    void testCreateProduct() {
        ProductCreateDto dto = ProductCreateDto.builder()
                .code("12345")
                .name("Test product")
                .price(BigDecimal.valueOf(19.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        Product createdProduct = new Product(1L, dto.getCode(), dto.getName(),
                dto.getPrice(), dto.getProductionDate(),dto.getExpirationDate());

        when(productService.createProduct(dto)).thenReturn(createdProduct);

        ResponseEntity<Product> response = productController.createProduct(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProduct, response.getBody());
        verify(productService, times(1)).createProduct(dto);
    }

    @Test
    void testUpdateProduct() {
        // Mock data
        Long productId = 1L;
        ProductUpdateRequestDto updateRequestDto = ProductUpdateRequestDto.builder()
                .code("12345")
                .name("Test Product")
                .price(BigDecimal.valueOf(200.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(15))
                .build();

        Product updatedProduct = Product.builder()
                .id(productId)
                .code("12345")
                .name("Test product")
                .price(BigDecimal.valueOf(19.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        when(productService.updateProduct(productId, updateRequestDto)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(productId, updateRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
        verify(productService, times(1)).updateProduct(productId, updateRequestDto);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }
}