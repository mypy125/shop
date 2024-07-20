package com.daniam;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String msg){
        super(msg);
    }
}
