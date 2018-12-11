package com.m2i.flexiflex.http.requests;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterRequest {
    public String email;
    public String password;

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static RegisterRequest buildFromJson (String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        return new RegisterRequest(email, password);
    }
}
