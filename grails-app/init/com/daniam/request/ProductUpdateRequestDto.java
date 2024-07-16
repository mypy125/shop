package com.daniam.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ProductUpdateRequestDto{
    private String code;
    private String name;
    private BigDecimal price;
    private LocalDate productionDate;
    private LocalDate expirationDate;
}
