package com.daniam.controller;

import com.daniam.service.StockService;
import com.daniam.domain.Product;
import com.daniam.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/{stockId}/products")
    public ResponseEntity<Stock> addProductToStock(@PathVariable Long stockId,
                                                   @RequestBody Product product,
                                                   @RequestParam int quantity
    ){
        Stock addProductStock = stockService.addProductToStock(stockId, product, quantity);
        return new ResponseEntity<>(addProductStock, HttpStatus.OK);
    }

    @DeleteMapping("/{stockId}/products")
    public ResponseEntity<Stock> removeProductFromStock(@PathVariable Long stockId,
                                                            @RequestBody Product product
    ){
        Stock removeProductFromeStock = stockService.removeProductFromStock(stockId, product);
        return new ResponseEntity<>(removeProductFromeStock, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock(){
        List<Stock> allStock = stockService.getAllStock();
        return new ResponseEntity<>(allStock, HttpStatus.OK);
    }
}
