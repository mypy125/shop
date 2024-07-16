package com.daniam.service;

import com.daniam.dto.ProductCreateDto;
import com.daniam.entity.Product;
import com.daniam.mapping.ProductMapping;
import com.daniam.repository.ProductRepository;
import com.daniam.request.ProductUpdateRequestDto;
import com.daniam.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapping productMapping;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        ProductCreateDto dto = ProductCreateDto.builder()
                .code("12345")
                .name("Test product")
                .price(BigDecimal.valueOf(19.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        Product product = product = Product.builder()
                .id(1L)
                .code("12345")
                .name("New Product")
                .price(dto.getPrice())
                .productionDate(dto.getProductionDate())
                .expirationDate(dto.getExpirationDate())
                .build();


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
        Product product = product = Product.builder()
                .id(1L)
                .code("12345")
                .name("Test Product")
                .price(BigDecimal.valueOf(19.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("12345", result.get().getCode());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getAllProducts() {
        List<Product> products = Arrays.asList(new Product(
                1L, "12345", "Test Product", BigDecimal.valueOf(19.99),
                LocalDate.now(),  LocalDate.now().plusDays(10)
        ));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void updateProduct() {
        ProductUpdateRequestDto request = ProductUpdateRequestDto.builder()
                .code("12345")
                .name("Test Product")
                .price(BigDecimal.valueOf(200.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(15))
                .build();

        Product product = product = Product.builder()
                .id(1L)
                .code("123")
                .name("Test Product")
                .price(BigDecimal.valueOf(22.99))
                .productionDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

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
        Product product = new Product(1L, "123", "Test Product", BigDecimal.valueOf(100.0), LocalDate.now(), LocalDate.now().plusDays(1));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }
}