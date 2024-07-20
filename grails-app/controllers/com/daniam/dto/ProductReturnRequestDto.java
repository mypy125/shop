package com.daniam.dto;

import com.daniam.Product;

public class ProductReturnRequestDto {
    private Long stockId;
    private Product product;


    public ProductReturnRequestDto(
            Long stockId, Product product
    ) {
        this.stockId = stockId;
        this.product = product;
    }

    public ProductReturnRequestDto(){}

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
