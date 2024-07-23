package com.daniam.shop.exception;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String msg){
        super(msg);
    }
}
