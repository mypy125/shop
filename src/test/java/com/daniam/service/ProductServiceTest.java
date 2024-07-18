package com.daniam.service;

import com.daniam.ApplicationStart;
import com.daniam.controller.dto.ProductCreateDto;
import com.daniam.controller.request.ProductUpdateRequestDto;
import com.daniam.domain.Product;
import com.daniam.mapping.ProductMapping;
import com.daniam.repository.ProductRepository;
import com.daniam.service.impl.ProductServiceImpl;
import org.grails.plugins.converters.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = ApplicationStart.class)
class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapping productMapping;

    private ProductCreateDto dto;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Long productId = 1L;
        ProductCreateDto dto = new ProductCreateDto(
                "12345", "Test product", BigDecimal.valueOf(19.99),
                LocalDate.now(), LocalDate.now().plusDays(10)
        );

        Product product = new Product(
                "12345", "New Product", dto.getPrice(),
                dto.getProductionDate(), dto.getExpirationDate()
        );
        product.setId(productId);
    }

    @Test
    void createProduct() {
        when(productRepository.existsByCode(dto.getCode())).thenReturn(false);
        when(productMapping.toEntity(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(dto);

        assertNotNull(result);
        assertEquals("12345", result.getCode());
        verify(productRepository, times(1)).existsByCode(dto.getCode());
        verify(productMapping, times(1)).toEntity(dto);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void getProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("12345", result.get().getCode());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getAllProducts() {
        List<Product> products = Arrays.asList(new Product(
                "12345", "Test Product", BigDecimal.valueOf(19.99),
                LocalDate.now(),  LocalDate.now().plusDays(10)
        ));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void updateProduct() {
        ProductUpdateRequestDto request = new ProductUpdateRequestDto(
                "12345", "Test Product", BigDecimal.valueOf(200.99),
                LocalDate.now(), LocalDate.now().plusDays(15)
        );

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProduct(1L, request);

        assertNotNull(result);
        assertEquals("12345", result.getCode());
        assertEquals(200.99, result.getPrice());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct() {
        Product product = new Product("123", "Test Product", BigDecimal.valueOf(100.0), LocalDate.now(), LocalDate.now().plusDays(1));
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }
}