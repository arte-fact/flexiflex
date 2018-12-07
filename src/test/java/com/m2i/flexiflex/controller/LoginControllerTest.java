package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.BaseItTest;
import com.m2i.flexiflex.controllers.auth.LoginController;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.repositories.users.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class LoginControllerTest extends BaseItTest {

    private static String testUserMail = "user@mail.com";
    private static String testUserPassword = "secret";

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Mock
    private  UserRepository userRepository;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void userCannotLoginWithBadPassword() {
        // GIVEN
        User user = new User();
        user.setEmail(testUserMail);
        user.setPassword(testUserPassword);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);

        ResponseEntity reference = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        Mockito.when(userRepository.existsByEmail(testUserMail)).thenReturn(true);
        Mockito.when(userRepository.getByEmail(testUserMail)).thenReturn(user);
        // WHEN
        ResponseEntity result = loginController.login(testUserMail, testUserPassword);
        // THEN
        Assertions.assertThat(result).isEqualToComparingFieldByField(reference); //MOCKITO

        Assert.assertEquals(reference,result);//JUNIT
    }

//    @Test
//    public void userCannotLoginWithBadPassword() throws Exception {
//        userServiceImp.deleteUserByMail(testUserMail);
//        userServiceImp.create(testUserMail, testUserPassword);
//
//
//        mvc.perform(post("/login")
//                .param(UserProperties.EMAIL, testUserMail)
//                .param(UserProperties.PASSWORD, "caca")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//
//        userServiceImp.deleteUserByMail(testUserMail);
//    }
//
//    @Test
//    public void userCanLoginWithPassword() throws Exception {
//        userServiceImp.create(testUserMail, testUserPassword);
//
//        mvc.perform(post("/login")
//                .param(UserProperties.EMAIL, testUserMail)
//                .param(UserProperties.PASSWORD, testUserPassword)
//                .contentType(APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//        userServiceImp.deleteUserByMail(testUserMail);
//    }
}