package com.daniam.controller;

import com.daniam.controller.dto.ProductCreateDto;
import com.daniam.controller.request.ProductUpdateRequestDto;
import com.daniam.domain.Product;
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

//@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product1;
    private Product product2;
    private Long productId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Product product1 = new Product(
                "12345", "Test product1", BigDecimal.valueOf(19.99),
                LocalDate.now(), LocalDate.now().plusDays(10)
        );
        product1.setId(productId);

        Product product2 = new Product(
                "12345", "Test product2", BigDecimal.valueOf(19.99),
                LocalDate.now(), LocalDate.now().plusDays(10)
        );
        product2.setId(2L);
    }

    @Test
    void testGetAllProducts() {

        List<Product> mockProducts = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProducts, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        when(productService.getProductById(productId)).thenReturn(Optional.of(product1));

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
        verify(productService, times(1)).getProductById(productId);

        Long nonExistentId = 100L;
        when(productService.getProductById(nonExistentId)).thenReturn(Optional.empty());

        ResponseEntity<Product> notFoundResponse = productController.getProductById(nonExistentId);

        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
        assertEquals(null, notFoundResponse.getBody());
    }

    @Test
    void testCreateProduct() {
        ProductCreateDto dto = new ProductCreateDto(
                "12345", "Test product", BigDecimal.valueOf(19.99),
                LocalDate.now(), LocalDate.now().plusDays(10)
        );

        Product createdProduct = new Product(dto.getCode(), dto.getName(),
                dto.getPrice(), dto.getProductionDate(),dto.getExpirationDate());
        createdProduct.setId(productId);
        when(productService.createProduct(dto)).thenReturn(createdProduct);

        ResponseEntity<Product> response = productController.createProduct(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProduct, response.getBody());
        verify(productService, times(1)).createProduct(dto);
    }

    @Test
    void testUpdateProduct() {
        ProductUpdateRequestDto updateRequestDto = new ProductUpdateRequestDto(
                "12345", "Test Product", BigDecimal.valueOf(200.99),
                LocalDate.now(), LocalDate.now().plusDays(15)
        );

        when(productService.updateProduct(productId, updateRequestDto)).thenReturn(product1);

        ResponseEntity<Product> response = productController.updateProduct(productId, updateRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
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