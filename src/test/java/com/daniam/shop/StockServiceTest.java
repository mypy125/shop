package com.daniam.shop;

import com.daniam.shop.domain.Product;
import com.daniam.shop.domain.Stock;
import com.daniam.shop.exception.StockNotFoundException;
import com.daniam.shop.service.impl.StockServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.daniam.shop.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stock = new Stock();
        stock.setId(1L);
        Product product = new Product(
                "12345", "Test product1", BigDecimal.valueOf(19.99),
                LocalDate.now(), LocalDate.now().plusDays(10)
        );
        product.setId(1L);
    }

//    @Test
//    void addProductToStock() {
//        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
//        when(stockRepository.save(stock)).thenReturn(stock);
//
//        Stock updatedStock = stockService.addProductToStock(1L, product, 10);
//
//        assertNotNull(updatedStock);
//        assertEquals(1, updatedStock.getProducts().size());
//        assertEquals(10, updatedStock.getProducts().get(product));
//        verify(stockRepository, times(1)).findById(1L);
//        verify(stockRepository, times(1)).save(stock);
//    }
//
//    @Test
//    void removeProductFromStock() {
//        stock.getProducts().put(product, 10);
//        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
//        when(stockRepository.save(stock)).thenReturn(stock);
//
//        Stock updatedStock = stockService.removeProductFromStock(1L, product);
//
//        assertNotNull(updatedStock);
//        assertEquals(0, updatedStock.getProducts().size());
//        verify(stockRepository, times(1)).findById(1L);
//        verify(stockRepository, times(1)).save(stock);
//    }

    @Test
    void getAllStock() {
        when(stockRepository.findAll()).thenReturn(List.of(stock));

        List<Stock> allStock = stockService.getAllStock();

        assertNotNull(allStock);
        assertEquals(1, allStock.size());
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    public void addProductToStock_StockNotFound() {
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(StockNotFoundException.class, () -> {
            stockService.addProductToStock(1L, product, 10);
        });
        verify(stockRepository, times(1)).findById(1L);
        verify(stockRepository, times(0)).save(any());
    }

    @Test
    public void removeProductFromStock_StockNotFound() {
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(StockNotFoundException.class, () -> {
            stockService.removeProductFromStock(1L, product);
        });
        verify(stockRepository, times(1)).findById(1L);
        verify(stockRepository, times(0)).save(any());
    }
}