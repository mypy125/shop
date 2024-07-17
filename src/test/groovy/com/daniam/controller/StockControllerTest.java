package com.daniam.controller;

import com.daniam.entity.Product;
import com.daniam.entity.Stock;
import com.daniam.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProductToStock() {
        Long stockId = 1L;
        Product product = new Product();
        int quantity = 10;
        Stock stock = new Stock();

        when(stockService.addProductToStock(stockId, product, quantity)).thenReturn(stock);

        ResponseEntity<Stock> response = stockController.addProductToStock(stockId, product, quantity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stock, response.getBody());
    }

    @Test
    void removeProductFromStock() {
        Long stockId = 1L;
        Product product = new Product();
        Stock stock = new Stock();

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