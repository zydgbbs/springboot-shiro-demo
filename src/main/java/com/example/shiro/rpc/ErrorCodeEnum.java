package com.example.shiro.rpc;


public enum ErrorCodeEnum {
    /**成功*/
    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    PARAM_ERROR(2, "param error"),

    PERMISSION_DENIED(3, "permission denied"),
    NEED_RE_LOGIN(4, "login required"),

    HANDLER_NOT_FOUND_ERROR(10, ""),
    HANDLER_CONFIG_ERROR(11, ""),
    PUSH_FAIL(12, "push fail"),

    ALREADY_ADMISSION(12, "already occupancy"),

    MAC_ADDRESS_NOT_EXIST(300, "mac地址不存在"),
    PASSWORD_ERROR(301, "password error"),
    MAC_ADDRESS_ERROR(302, "mac地址错误"),



    SYSTEM_ERROR(999, "system error");

    private int code;
    private String desc;

    private ErrorCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorCodeEnum getErrorCodeEnum(int code) {
        ErrorCodeEnum[] values = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorCodeEnum : values) {
            if (errorCodeEnum.getCode() == code) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    public static String getDesc(int code) {
        ErrorCodeEnum[] values = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorCodeEnum : values) {
            if (errorCodeEnum.getCode() == code) {
                return errorCodeEnum.getDesc();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}