package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.controllers.RegisterController;
import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    private String testUserMail = "user@mail.com";
    private String testUserPassword = "secret";

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private MockMvc mvc;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void nonUserCanRegister() throws Exception{
        userServiceImp.deleteUserByMail(testUserMail);

        mvc.perform(post("/register")
                .param(UserProperties.EMAIL, testUserMail)
                .param(UserProperties.PASSWORD, testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        userServiceImp.deleteUserByMail(testUserMail);

    }

    @Test
    public void registeredUserCannotRegister() throws Exception {
        userServiceImp.create(testUserMail, testUserPassword);

        mvc.perform(post("/register")
                .param(UserProperties.EMAIL, testUserMail)
                .param(UserProperties.PASSWORD, testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());

        userServiceImp.deleteUserByMail(testUserMail);

    }
}