
package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.persistence.UserPersistence;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailValidationController.class)
public class EmailValidationControllerTest {

    private String testUserMail = "user@mail.com";
    private String testUserPassword = "secret";

    @Autowired
    private MockMvc mvc;
    private Session hbsession = HibernateSession.getSession();

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void emailValidationRequestParamAbsent() throws Exception {
        String url = "/email_validation";
        this.mvc.perform(get(url)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emailValidationBadUuid() throws Exception {
        UserPersistence.create(testUserMail, testUserPassword);

        String url = "/email_validation?key1=" + "nimportequoi" + "&key2=" + "nimportequoi";
        this.mvc.perform(get(url))
                .andExpect(status().isBadRequest()).andDo(print());

        UserPersistence.delete(testUserMail);
    }

    @Test
    public void emailValidationBadValidationToken() throws Exception {
        UserEntity userEntity = UserPersistence.create(testUserMail, testUserPassword);

        String url = "/email_validation?key1=" + userEntity.getUuid() + "&key2=" + "nimportequoi";
        this.mvc.perform(get(url))
                .andExpect(status().isBadRequest()).andDo(print());

        UserPersistence.delete(testUserMail);
    }

    @Test
    public void emailValidationGoodParameters() throws Exception {
        UserEntity userEntity = UserPersistence.create(testUserMail, testUserPassword);

        String url = "/email_validation?uuid=" + userEntity.getUuid() + "&token=" + userEntity.getValidationToken();
        this.mvc.perform(get(url))
                .andExpect(status().isOk()).andDo(print());

        UserPersistence.delete(testUserMail);
    }
}
