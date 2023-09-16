package com.henrique.java.exception.advice;

import org.henrique.java.backend.DTO.ErrorDTO;
import org.henrique.java.backend.Exception.ProductNotFoundException;
import org.henrique.java.backend.Exception.UserErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
        errorDTO.setTimestamp(LocalDateTime.now());
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
        errorDTO.setTimestamp(LocalDateTime.now());
        errorDTO.setMessage(userErrorException.getMessage());
        return errorDTO;
    }
}
