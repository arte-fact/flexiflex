package com.m2i.flexiflex.controller;

import com.google.gson.Gson;
import com.m2i.flexiflex.controllers.auth.LoginController;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class, Mockito.class})
@WebAppConfiguration
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @Mock
    private UserService userServiceImp;

    @Autowired
    @InjectMocks
    private LoginController loginController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .build();
    }

    @Test
    public void userCannotLoginWithBadPassword() throws Exception {

        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("secret");

        Gson gson = new Gson();
        String jsonRequestBody = gson.toJson(user);

        mockMvc.perform(post("/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonRequestBody)
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void userCanLoginWithPassword() throws Exception {

        String mail = "user@mail.com";

        User user = new User();
        user.setEmail(mail);
        user.setPassword("secret");

        Gson gson = new Gson();
        String jsonRequestBody = gson.toJson(user);

        user.setEmailValidation(true);
        Mockito.when(userServiceImp.existsByMail(mail)).thenReturn(true);
        Mockito.when(userServiceImp.getByMail(mail)).thenReturn(user);

        Assert.assertTrue(userServiceImp.existsByMail(mail));


        mockMvc.perform(post("/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonRequestBody)
        ).andExpect(status().isOk());
    }
}