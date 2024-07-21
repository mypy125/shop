package com.daniam;

import com.daniam.Product;
import com.daniam.Store;
import com.daniam.dto.StoreCreateRequestDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Store save(Store store);
    Store createStore(StoreCreateRequestDto dto);
    Optional<Store> findById(Long storeId);
    Store addProductToStore(Long storeId, Product product, int quantity);
    Store removeProductFromStore(Long storeId, Product product);
    List<Store> getAllStores();
    void checkAndReturnExpiredProducts();
    void sellProduct(Long storeId, Product product, int quantity);
    void returnProductToStock(Long storeId, Long stockId, Product product);

}
