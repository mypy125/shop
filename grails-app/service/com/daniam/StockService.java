package com.daniam;

import com.daniam.Product;
import com.daniam.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    Stock save(Stock stock);
    Optional<Stock> findById(Long id);
    void delete(Long id);
    Stock addProductToStock(Long stockId, Product product, int quantity);
    Stock removeProductFromStock(Long stockId, Product product);
    List<Stock> getAllStock();
}
