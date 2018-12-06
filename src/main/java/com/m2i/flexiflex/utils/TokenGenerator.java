package com.m2i.flexiflex.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

public class TokenGenerator {

    public static String GetTokenSHA256(){
        String token = null;
        try {
            UUID uuid = UUID.randomUUID();
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            token = String.format("%040x", new BigInteger(1, salt.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String GetToken(){
        return UUID.randomUUID().toString();
    }
}
