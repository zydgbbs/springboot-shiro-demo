package com.example.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public void bindException(BindException e){
        String result = e.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(";"));
        System.out.println("绑定异常："+result);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException e) {
        System.out.println("非法异常");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public void handleUnauthorizedException(UnauthorizedException e) {
        System.out.println("授权失败");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        e.printStackTrace();
        System.out.println("异常");
    }

}
