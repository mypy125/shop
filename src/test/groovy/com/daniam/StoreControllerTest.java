package com.daniam;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.daniam.dto.ProductReturnRequestDto;
import com.daniam.dto.ProductSaleRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    @Autowired
    private ObjectMapper objectMapper;

    private Store store;
    private Product product;

    @BeforeEach
    void setUp() {
        store = new Store();
        store.setId(1L);
        store.setName("Test Store");

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
    }

    @Test
    void getAllStores() throws Exception {
        List<Store> stores = Arrays.asList(store);

        when(storeService.getAllStores()).thenReturn(stores);

        mockMvc.perform(get("/api/stores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(store.getId()))
                .andExpect(jsonPath("$[0].name").value(store.getName()));
    }

    @Test
    void getStoreById_WhenStoreExists() throws Exception {
        when(storeService.findById(store.getId())).thenReturn(Optional.of(store));

        mockMvc.perform(get("/api/stores/{storeId}", store.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(store.getId()))
                .andExpect(jsonPath("$.name").value(store.getName()));
    }

    @Test
    void getStoreById_WhenStoreDoesNotExist() throws Exception {
        when(storeService.findById(store.getId())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/stores/{storeId}", store.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void createStore() throws Exception {
//        Store store = new Store();
//        String storeJson = objectMapper.writeValueAsString(store);
//
//        mockMvc.perform(post("/stores")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(storeJson))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").exists());
//    }
//
//    @Test
//    void updateStore() throws Exception{
//        when(storeService.save(any(Store.class))).thenReturn(store);
//
//        mockMvc.perform(put("/api/stores/{storeId}", store.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(store)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(store.getId()))
//                .andExpect(jsonPath("$.name").value(store.getName()));
//    }

    @Test
    void addProductToStore() throws Exception{
        int quantity = 10;
        when(storeService.addProductToStore(store.getId(), product, quantity)).thenReturn(store);

        mockMvc.perform(post("/api/stores/{storeId}/products", store.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("quantity", String.valueOf(quantity))
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(store.getId()))
                .andExpect(jsonPath("$.name").value(store.getName()));
    }

    @Test
    void removeProductFromStore() throws Exception{
        when(storeService.removeProductFromStore(store.getId(), product)).thenReturn(store);

        mockMvc.perform(delete("/api/stores/{storeId}/products", store.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(store.getId()))
                .andExpect(jsonPath("$.name").value(store.getName()));
    }

    @Test
    void returnProductToStock() throws Exception{
        ProductReturnRequestDto request = new ProductReturnRequestDto();
        request.setStockId(1L);
        request.setProduct(product);

        mockMvc.perform(post("/api/stores/{storeId}/return", store.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void sellProduct() throws Exception{
        ProductSaleRequestDto request = new ProductSaleRequestDto();
        request.setProduct(product);
        request.setQuantity(5);

        mockMvc.perform(post("/api/stores/{storeId}/sell", store.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void checkAndReturnExpiredProducts() throws Exception{
        doNothing().when(storeService).checkAndReturnExpiredProducts();

        mockMvc.perform(post("/api/stores/check-expired")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}