package com.daniam.shop.controller.dto;

import com.daniam.shop.domain.Product;

public class ProductSaleRequestDto {
    private Product product;
    private int quantity;

    public ProductSaleRequestDto(
            Product product, int quantity
    ) {
        this.product = product;
        this.quantity = quantity;
    }
    public ProductSaleRequestDto(){}
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
