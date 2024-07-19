package com.daniam.service.impl;

import com.daniam.domain.Product;
import com.daniam.domain.Stock;
import com.daniam.service.StockService;
import com.daniam.exception.StockNotFoundException;
import com.daniam.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return stockRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

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

}
