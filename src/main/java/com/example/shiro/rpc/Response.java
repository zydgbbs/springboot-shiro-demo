package com.example.shiro.rpc;


import lombok.Data;

@Data
public class Response<T> {
    private int code = 0;
    private String msg = "success";
    private T data;
}
