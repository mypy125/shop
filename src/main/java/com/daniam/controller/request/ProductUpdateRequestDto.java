package com.daniam.controller.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class ProductUpdateRequestDto{
    private String code;
    private String name;
    private BigDecimal price;
    private LocalDate productionDate;
    private LocalDate expirationDate;
}
