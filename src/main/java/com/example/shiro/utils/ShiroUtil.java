package com.example.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroUtil {
    //添加user的密码加密方法
    public static String SysMd5(String username,String password) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials =password;//密码原值
        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        return hash.toString();
    }
}
