package com.daniam.shop.controller.dto;

import com.daniam.shop.domain.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class StoreCreateRequestDto {
    private String password;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotNull(message = "Products are required")
    private Map<Product, Integer> products;

    public StoreCreateRequestDto(String password,
                                 String name,
                                 String address,
                                 Map<Product, Integer> products
    ) {
        this.password = password;
        this.name = name;
        this.address = address;
        this.products = products;
    }

    public StoreCreateRequestDto(){}
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
