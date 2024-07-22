package com.daniam.service.impl;

import com.daniam.domain.Product;
import com.daniam.domain.Stock;
import com.daniam.domain.Store;
import com.daniam.controller.dto.StoreCreateRequestDto;
import com.daniam.exception.StockNotFoundException;
import com.daniam.exception.StoreArgumentException;
import com.daniam.mapping.StoreMapping;
import com.daniam.repository.StockRepository;
import com.daniam.repository.StoreRepository;
import com.daniam.service.StoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Этот сервисный класс управляет магазинами и их взаимодействием с запасами на складах.
 */
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final StockRepository stockRepository;
    private StoreMapping storeMapping;

    public StoreServiceImpl(StoreRepository storeRepository, StockRepository stockRepository) {
        this.storeRepository = storeRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store createStore(StoreCreateRequestDto dto) {
        return storeRepository.save(storeMapping.toEntity(dto));
    }

    @Override
    public Optional<Store> findById(Long storeId) {
        return Optional.ofNullable(storeRepository.findById(storeId).orElseThrow(() ->
                new StockNotFoundException("Store not found")));
    }

    @Override
    public Store addProductToStore(Long storeId, Product product, int quantity) {
        Store store = storeRepository.findById(storeId).orElseThrow(() ->
                new StockNotFoundException("Store not found"));
        store.getProducts().put(product, quantity);
        return storeRepository.save(store);
    }

    @Override
    public Store removeProductFromStore(Long storeId, Product product) {
        Store store = storeRepository.findById(storeId).orElseThrow(() ->
                new StockNotFoundException("Store not found"));
        store.getProducts().remove(product);
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    /**
     * Этот метод выполняет следующие операции:
     * Получает магазин по его идентификатору. Если хранилище не найдено, выдается исключение NoSuchElementException.
     * Получает список товаров в магазине. Если количество товара в магазине меньше запрошенного, выдается исключение IllegalArgumentException.
     * Обновляет количество товара в магазине. Если обновленное количество равно нулю, товар удаляется из магазина.
     * Сохраняет обновленную информацию о магазине в репозитории.
     * @param storeId  идентификатор магазина, в котором продается товар
     * @param product продукт, который будет продан.
     * @param quantity количество товара, которое будет продано.
     * @throws StoreArgumentException, если на складе недостаточно товара
     */
    @Override
    public void sellProduct(Long storeId, Product product, int quantity) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Map<Product, Integer> products = store.getProducts();
        int currentQuantity = products.getOrDefault(product, 0);
        if (currentQuantity < quantity) {
            throw new StoreArgumentException("Not enough product in stock");
        }
        products.put(product, currentQuantity - quantity);
        if (products.get(product) == 0) {
            products.remove(product);
        }
        storeRepository.save(store);
    }

    /**
     * Этот метод выполняет следующие операции:
     * Получает магазин и репозиторий по их идентификаторам. Если какой-либо из них не найден, выдается исключение NoSuchElementException.
     * Проверяет наличие товара в магазине. Если продукт не найден, выдается исключение IllegalArgumentException.
     * Удаляет товар из магазина и добавляет его в репозиторий в соответствующем количестве.
     * Сохраняет обновленную информацию о магазине и репозитории в соответствующих репозиториях.
     * @param storeId идентификатор магазина, из которого возвращается товар
     * @param stockId  идентификатор склада, в который возвращается товар
     * @param product  товар, который необходимо вернуть.
     * @throws StockNotFoundException, если склад или репозиторий не найден
     * @throws IllegalArgumentException, если товар не найден в магазине
     */
    @Override
    public void returnProductToStock(Long storeId, Long stockId, Product product) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Stock stock = stockRepository.findById(stockId).orElseThrow();

        int quantity = store.getProducts().getOrDefault(product, 0);
        if (quantity == 0) {
            throw new StockNotFoundException("Product not found in store");
        }

        store.getProducts().remove(product);
        stock.getProducts().put(product, stock.getProducts().getOrDefault(product, 0) + quantity);

        storeRepository.save(store);
        stockRepository.save(stock);
    }

    /**
     * проверяет все магазины на наличие продуктов с истекшим сроком годности и возвращает их на склад
     * Для каждого продукта и его количества в магазине проверяется, истек ли срок годности продукта.
     * Если у продукта есть дата истечения срока годности и эта дата уже прошла
     * В репозитории ищется склад, который содержит этот продукт. Если такой склад не найден, выбрасывается исключение
     * Продукт удаляется из списка продуктов магазина.
     * Изменения в объекте склада и магазина сохраняются в соответствующие репозитории.
     */
    @Override
    public void checkAndReturnExpiredProducts() {
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            Map<Product, Integer> expiredProducts = new HashMap<>();
            store.getProducts().forEach((product, quantity) -> {
                if (product.getExpirationDate() != null && product.getExpirationDate().isBefore(LocalDate.now())) {
                    expiredProducts.put(product, quantity);
                }
            });

            expiredProducts.forEach((product, quantity) -> {
                Stock stock = stockRepository.findAll().stream()
                        .filter(stc -> stc.getProducts().containsKey(product))
                        .findFirst()
                        .orElseThrow(() -> new StockNotFoundException("Stock Not Found"));
                stock.getProducts().put(product, stock.getProducts().getOrDefault(product, 0) + quantity);
                store.getProducts().remove(product);
                stockRepository.save(stock);
                storeRepository.save(store);
            });
        }
    }
}
