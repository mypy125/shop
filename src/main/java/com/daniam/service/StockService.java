package com.daniam.service;

import com.daniam.domain.Product;
import com.daniam.domain.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {
    Stock addProductToStock(Long stockId, Product product, int quantity);
    Stock removeProductFromStock(Long stockId, Product product);
    List<Stock> getAllStock();
}
