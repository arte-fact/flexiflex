package com.m2i.flexiflex.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptHolder {
    private static BcryptHolder ourInstance = new BcryptHolder();

    public static BcryptHolder getInstance() {
        return ourInstance;
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    public BcryptHolder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
}
