package org.henrique.java.backend.Exception;

public class ShopNotFoundException extends RuntimeException{
    public ShopNotFoundException(String msg){
        super(msg);
    }
}
