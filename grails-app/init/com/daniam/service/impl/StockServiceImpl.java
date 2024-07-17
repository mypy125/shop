package com.daniam.service.impl;

import com.daniam.entity.Product;
import com.daniam.entity.Stock;
import com.daniam.exception.StockNotFoundException;
import com.daniam.repository.StockRepository;
import com.daniam.service.StockService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    public Stock addProductToStock(Long stockId, Product product, int quantity) {
        Stock addProductStock = stockRepository.findById(stockId).orElseThrow(()->
                new StockNotFoundException("Stock Not Found"));
        addProductStock.getProducts().put(product, quantity);
        return stockRepository.save(addProductStock);
    }

    @Override
    public Stock removeProductFromStock(Long stockId, Product product) {
        Stock removProductStock = stockRepository.findById(stockId).orElseThrow(()->
                new StockNotFoundException("Stock Not Found"));
        removProductStock.getProducts().remove(product);
        return stockRepository.save(removProductStock);
    }

    @Override
    public List<Stock> getAllStock() {
        return List.of((Stock) stockRepository.findAll());
    }
}
