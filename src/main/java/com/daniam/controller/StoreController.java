package com.daniam.controller;

import com.daniam.controller.dto.ProductReturnRequestDto;
import com.daniam.controller.dto.ProductSaleRequestDto;
import com.daniam.domain.Product;
import com.daniam.domain.Store;
import com.daniam.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long storeId
    ) {
        Optional<Store> store = storeService.findById(storeId);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store
    ) {
        Store createdStore = storeService.save(store);
        return ResponseEntity.ok(createdStore);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<Store> updateStore(@PathVariable Long storeId,
                                             @RequestBody Store store
    ) {
        store.setId(storeId);
        Store updatedStore = storeService.save(store);
        return ResponseEntity.ok(updatedStore);
    }

    @PostMapping("/{storeId}/products")
    public ResponseEntity<Store> addProductToStore(@PathVariable Long storeId,
                                                   @RequestBody Product product,
                                                   @RequestParam int quantity
    ) {
        return ResponseEntity.ok(storeService.addProductToStore(storeId, product, quantity));
    }

    @DeleteMapping("/{storeId}/products")
    public ResponseEntity<Store> removeProductFromStore(@PathVariable Long storeId,
                                                        @RequestBody Product product
    ) {
        return ResponseEntity.ok(storeService.removeProductFromStore(storeId, product));
    }

    @PostMapping("/{storeId}/return")
    public ResponseEntity<Void> returnProductToStock(@PathVariable Long storeId,
                                                          @RequestBody ProductReturnRequestDto request
    ) {
        storeService.returnProductToStock(storeId, request.getStockId(), request.getProduct());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{storeId}/sell")
    public ResponseEntity<Void> sellProduct(@PathVariable Long storeId,
                                            @RequestBody ProductSaleRequestDto request
    ) {
        storeService.sellProduct(storeId, request.getProduct(), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check-expired")
    public ResponseEntity<Void> checkAndReturnExpiredProducts() {
        storeService.checkAndReturnExpiredProducts();
        return ResponseEntity.noContent().build();
    }
}
