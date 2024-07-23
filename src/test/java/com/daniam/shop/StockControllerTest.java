package com.daniam.shop;

import com.daniam.shop.controller.StockController;
import com.daniam.shop.domain.Product;
import com.daniam.shop.domain.Stock;
import com.daniam.shop.service.StockService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    private Product product;
    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(
                "12345", "Test product1", BigDecimal.valueOf(19.99),
                LocalDate.now(), LocalDate.now().plusDays(10)
        );

        stock = new Stock();
        stock.setId(1L);
    }

    @Test
    void addProductToStock() {
        Long stockId = 1L;
        int quantity = 10;

        when(stockService.addProductToStock(stockId, product, quantity)).thenReturn(stock);

        ResponseEntity<Stock> response = stockController.addProductToStock(stockId, product, quantity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stock, response.getBody());
    }

    @Test
    void removeProductFromStock() {
        Long stockId = 1L;

        when(stockService.removeProductFromStock(stockId, product)).thenReturn(stock);

        ResponseEntity<Stock> response = stockController.removeProductFromStock(stockId, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stock, response.getBody());
    }

    @Test
    void getAllStock() {
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock());

        when(stockService.getAllStock()).thenReturn(stocks);

        ResponseEntity<List<Stock>> response = stockController.getAllStock();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stocks, response.getBody());
    }
}