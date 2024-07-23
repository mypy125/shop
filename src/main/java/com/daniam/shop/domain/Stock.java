package com.daniam.shop.domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "stock")
public class Stock extends BaseEntity<Long> {
    private String code;
    private String name;

    @ElementCollection
    @CollectionTable(name = "stock_products", joinColumns = @JoinColumn(name = "stock_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

//----------------------------------------------------------------get_and_set

    public Stock(String code, String name,
                 Map<Product, Integer> products
    ) {
        this.code = code;
        this.name = name;
        this.products = new HashMap<>();
    }
    public Stock(){}
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Stock stock = (Stock) o;
        return Objects.equals(code, stock.code) && Objects.equals(name, stock.name) && Objects.equals(products, stock.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, products);
    }
}
