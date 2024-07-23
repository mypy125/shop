package com.daniam.shop.exception;

public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(String msg){
        super(msg);
    }
}
