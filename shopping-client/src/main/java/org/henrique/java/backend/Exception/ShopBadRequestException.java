package org.henrique.java.backend.Exception;

public class ShopBadRequestException extends RuntimeException{
    public ShopBadRequestException(String msg){
        super(msg);
    }
}
