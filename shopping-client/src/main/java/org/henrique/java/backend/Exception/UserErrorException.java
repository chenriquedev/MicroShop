package org.henrique.java.backend.Exception;

public class UserErrorException extends RuntimeException{

    public UserErrorException(String msg){
        super(msg);
    }
}
