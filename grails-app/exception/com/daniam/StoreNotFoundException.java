package com.daniam;

public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(String msg){
        super(msg);
    }
}
