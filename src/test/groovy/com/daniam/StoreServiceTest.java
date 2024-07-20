package com.daniam;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.*;

import com.daniam.impl.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StoreServiceImpl storeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Store store = new Store();
        when(storeRepository.save(store)).thenReturn(store);

        Store savedStore = storeService.save(store);

        assertThat(savedStore).isNotNull();
        verify(storeRepository, times(1)).save(store);
    }

    @Test
    void findById() {
        Store store = new Store();
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        Optional<Store> foundStore = storeService.findById(1L);

        assertThat(foundStore).isPresent();
        assertThat(foundStore.get()).isEqualTo(store);
        verify(storeRepository, times(1)).findById(1L);
    }

    @Test
    void addProductToStore() {
        Store store = new Store();
        Product product = new Product();
        store.setProducts(new HashMap<>());
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(storeRepository.save(store)).thenReturn(store);

        Store updatedStore = storeService.addProductToStore(1L, product, 10);

        assertThat(updatedStore.getProducts().get(product)).isEqualTo(10);
        verify(storeRepository, times(1)).findById(1L);
        verify(storeRepository, times(1)).save(store);
    }

    @Test
    void removeProductFromStore() {
        Store store = new Store();
        Product product = new Product();
        Map<Product, Integer> products = new HashMap<>();
        products.put(product, 10);
        store.setProducts(products);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(storeRepository.save(store)).thenReturn(store);

        Store updatedStore = storeService.removeProductFromStore(1L, product);

        assertThat(updatedStore.getProducts().get(product)).isNull();
        verify(storeRepository, times(1)).findById(1L);
        verify(storeRepository, times(1)).save(store);
    }

    @Test
    void getAllStores() {
        Store store1 = new Store();
        Store store2 = new Store();
        List<Store> stores = Arrays.asList(store1, store2);

        when(storeRepository.findAll()).thenReturn(stores);

        List<Store> result = storeService.getAllStores();

        assertThat(result).hasSize(2).contains(store1, store2);
        verify(storeRepository, times(1)).findAll();
    }

//    @Test
//    void checkAndReturnExpiredProducts() {
//        Store store = new Store();
//        Product product = new Product();
//        product.setExpirationDate(LocalDate.now().minusDays(1));
//        Map<Product, Integer> products = new HashMap<>();
//        products.put(product, 10);
//        store.setProducts(products);
//        List<Store> stores = Collections.singletonList(store);
//        when(storeRepository.findAll()).thenReturn(stores);
//        Stock stock = new Stock();
//        Map<Product, Integer> stockProducts = new HashMap<>();
//        stock.setProducts(stockProducts);
//        when(stockRepository.findAll()).thenReturn(Collections.singletonList(stock));
//        when(storeRepository.save(store)).thenReturn(store);
//        when(stockRepository.save(stock)).thenReturn(stock);
//
//        storeService.checkAndReturnExpiredProducts();
//
//        assertThat(store.getProducts().get(product)).isNull();
//        assertThat(stock.getProducts().get(product)).isEqualTo(10);
//        verify(storeRepository, times(1)).findAll();
//        verify(storeRepository, times(1)).save(store);
//        verify(stockRepository, times(1)).save(stock);
//    }

    @Test
    void sellProduct() {
        Store store = new Store();
        Product product = new Product();
        Map<Product, Integer> products = new HashMap<>();
        products.put(product, 10);
        store.setProducts(products);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(storeRepository.save(store)).thenReturn(store);

        storeService.sellProduct(1L, product, 5);

        assertThat(store.getProducts().get(product)).isEqualTo(5);
        verify(storeRepository, times(1)).findById(1L);
        verify(storeRepository, times(1)).save(store);
    }

    @Test
    void returnProductToStock() {
        Store store = new Store();
        Stock stock = new Stock();
        Product product = new Product();
        Map<Product, Integer> storeProducts = new HashMap<>();
        storeProducts.put(product, 10);
        store.setProducts(storeProducts);
        Map<Product, Integer> stockProducts = new HashMap<>();
        stock.setProducts(stockProducts);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(stockRepository.findById(2L)).thenReturn(Optional.of(stock));
        when(storeRepository.save(store)).thenReturn(store);
        when(stockRepository.save(stock)).thenReturn(stock);

        storeService.returnProductToStock(1L, 2L, product);

        assertThat(store.getProducts().get(product)).isNull();
        assertThat(stock.getProducts().get(product)).isEqualTo(10);
        verify(storeRepository, times(1)).findById(1L);
        verify(stockRepository, times(1)).findById(2L);
        verify(storeRepository, times(1)).save(store);
        verify(stockRepository, times(1)).save(stock);
    }
}