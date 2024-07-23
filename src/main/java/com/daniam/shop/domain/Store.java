package com.daniam.shop.domain;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "store")
public class Store extends BaseEntity<Long> {
    private String password;
    private String name;
    private String address;

    @ElementCollection
    @CollectionTable(name = "store_products", joinColumns = @JoinColumn(name = "store_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

//-------------------------------------------------------------get_and_set

    public Store(String password,
                 String name,
                 String address,
                 Map<Product, Integer> products
    ) {
        this.password = password;
        this.name = name;
        this.address = address;
        this.products = new HashMap<>();
    }
    public Store(){}
    public String getCode() {
        return password;
    }

    public void setCode(String code) {
        this.password = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(password, store.password) && Objects.equals(name, store.name) && Objects.equals(address, store.address) && Objects.equals(products, store.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, name, address, products);
    }
}
