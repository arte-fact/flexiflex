package com.m2i.flexiflex.http.responses;

public class LoginResponse {
    public String uuid;
    public String token;
    public LoginResponse(String uuid, String token) {
        this.uuid = uuid;
        this.token = token;
    }
}
