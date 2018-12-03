package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.service.UserServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)

public class AuthControllerTest {

    private static String testUserMail = "user@mail.com";
    private static String testUserPassword = "secret";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserServiceImp userServiceImp;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void nonUserCannotLogin() throws Exception {
        userServiceImp.deleteUserByMail(testUserMail);

        mvc.perform(post("/login")
                .param(UserProperties.EMAIL, "toto@caca.toto")
                .param(UserProperties.PASSWORD, testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized()).andDo(print());
    }

    @Test
    public void userCannotLoginWithBadPassword() throws Exception {
        userServiceImp.deleteUserByMail(testUserMail);
        userServiceImp.create(testUserMail, testUserPassword);


        mvc.perform(post("/login")
                .param(UserProperties.EMAIL, testUserMail)
                .param(UserProperties.PASSWORD, "caca")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        userServiceImp.deleteUserByMail(testUserMail);
    }

    @Test
    public void userCanLoginWithPassword() throws Exception {
        userServiceImp.create(testUserMail, testUserPassword);

        mvc.perform(post("/login")
                .param(UserProperties.EMAIL, testUserMail)
                .param(UserProperties.PASSWORD, testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        userServiceImp.deleteUserByMail(testUserMail);
    }
}