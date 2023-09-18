package com.henrique.java.exception.advice;

import org.henrique.java.backend.DTO.ErrorDTO;
import org.henrique.java.backend.Exception.ProductNotFoundException;
import org.henrique.java.backend.Exception.ShopBadRequestException;
import org.henrique.java.backend.Exception.ShopNotFoundException;
import org.henrique.java.backend.Exception.UserErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice(basePackages = "com.henrique.java.controller")
public class ShoppingControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleUserNotFound(
            ProductNotFoundException userNotFoundException
    ){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(userNotFoundException.getMessage());
        errorDTO.setTimestamp(converterData());
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserErrorException.class)
    public ErrorDTO handleUserNotFound(
            UserErrorException userErrorException
    ){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setTimestamp(converterData());
        errorDTO.setMessage(userErrorException.getMessage());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ShopNotFoundException.class)
    public ErrorDTO handleShopNotFound(ShopNotFoundException shopNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setTimestamp(converterData());

        errorDTO.setMessage(shopNotFoundException.getMessage());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ShopBadRequestException.class)
    public ErrorDTO handleShopBadRequest(ShopBadRequestException shopBadRequestException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setTimestamp(converterData());

        errorDTO.setMessage(shopBadRequestException.getMessage());
        return errorDTO;
    }

    public LocalDateTime converterData(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = now.format(formatter);
        return LocalDateTime.parse(formattedDate, formatter);
    }
}
