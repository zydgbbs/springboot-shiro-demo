package com.example.shiro.rpc;


public class Results {
    public static <T> Response<T> newSuccessResponse(T data) {
        return newResponse(data, 0, "success");
    }

    public static <T> Response<T> newFailedResponse(Integer code, String message) {
        return newResponse(null, code, message);
    }

    public static <T> Response<T> newFailedResponse(ErrorCodeEnum errorCodeEnum) {
        return newResponse(null, errorCodeEnum.getCode(), errorCodeEnum.getDesc());
    }

    public static <T> Response<T> newFailedResponse(ErrorCodeEnum errorCodeEnum,String message) {
        return newResponse(null, errorCodeEnum.getCode(), message);
    }

    public static <T> Response<T> newResponse(T data, Integer code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(message);
        response.setData(data);
        return response;
    }
}