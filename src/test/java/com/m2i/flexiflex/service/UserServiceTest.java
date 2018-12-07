package com.m2i.flexiflex.service;

import com.m2i.flexiflex.BaseItTest;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.repositories.users.UserRepository;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

//@RunWith(MockitoJUnitRunner.class)
//@ActiveProfiles("test")
//public class UserServiceTest extends BaseItTest {
//
//    private static String testUserMail = "user@mail.com";
//    private static String testUserPassword = "secret";
//
//    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
//            MediaType.APPLICATION_JSON.getType(),
//            MediaType.APPLICATION_JSON.getSubtype(),
//            Charset.forName("utf8"));
//
//    @Mock
//    private  UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImp userServiceImp;
//
//    @Before
//    public void setup() {
//        initMocks(this);
//    }
//
//    @Test
//    public void should_get_userByMail() {
//        // GIVEN
//        User user = new User();
//        user.setEmail(testUserMail);
//        user.setPassword(testUserPassword);
//
//        Mockito.when(userRepository.getByEmail(testUserMail)).thenReturn(user);
//        // WHEN
//        User result = userServiceImp.getByMail(testUserMail);
//        // THEN
//        Assertions.assertThat(result).isEqualTo(user); //MOCKITO
//
//        Assert.assertEquals(user,result);//JUNIT
//    }

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
//}