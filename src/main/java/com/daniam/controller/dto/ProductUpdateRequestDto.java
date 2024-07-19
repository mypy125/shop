package com.daniam.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductUpdateRequestDto{
    private String code;
    private String name;
    private BigDecimal price;
    private LocalDate productionDate;
    private LocalDate expirationDate;

    public ProductUpdateRequestDto(){}
    public ProductUpdateRequestDto(String code,
                                   String name,
                                   BigDecimal price,
                                   LocalDate productionDate,
                                   LocalDate expirationDate) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
