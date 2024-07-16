package com.daniam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String code;
    private String name;
    private BigDecimal price;
    private LocalDate productionDate;
    private LocalDate expirationDate;
    private int quantity;

}
