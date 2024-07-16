package com.daniam.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductCreateDto(
        String code,
        String name,
        BigDecimal price,
        LocalDate productionDate,
        LocalDate expirationDate,
        int quantity
) {
}
