package com.daniam.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class ProductCreateDto{
    private String code;
    private String name;
    private BigDecimal price;
    private LocalDate productionDate;
    private LocalDate expirationDate;
}
