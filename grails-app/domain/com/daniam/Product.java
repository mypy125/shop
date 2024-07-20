package com.daniam;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product extends BaseEntity<Long>{
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate productionDate;

    private LocalDate expirationDate;


//-----------------------------------------------get_and_set

    public Product(String code,
                   String name,
                   BigDecimal price,
                   LocalDate productionDate,
                   LocalDate expirationDate
    ) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }
    public Product(){}
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(productionDate, product.productionDate) && Objects.equals(expirationDate, product.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price, productionDate, expirationDate);
    }
}
