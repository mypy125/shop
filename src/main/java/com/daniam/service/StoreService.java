package com.daniam.service;

import com.daniam.domain.Product;
import com.daniam.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Store save(Store store);
    Optional<Store> findById(Long storeId);
    Store addProductToStore(Long storeId, Product product, int quantity);
    Store removeProductFromStore(Long storeId, Product product);
    List<Store> getAllStores();
    void checkAndReturnExpiredProducts();
    void sellProduct(Long storeId, Product product, int quantity);
    void returnProductToStock(Long storeId, Long stockId, Product product);

}
