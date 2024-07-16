package com.daniam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository {
    private String code;
    private String name;
    private Map<Product, Integer> products;
}
