package com.m2i.flexiflex.http.requests;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginRequest buildFromJson (String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        return new LoginRequest(email, password);
    }
}
