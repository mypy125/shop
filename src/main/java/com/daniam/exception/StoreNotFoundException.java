package com.daniam.exception;

public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(String msg){
        super(msg);
    }
}
