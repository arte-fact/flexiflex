
package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.controllers.auth.EmailValidationController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailValidationController.class)
public class EmailValidationControllerTest {
//
//    private String testUserMail = "user@mail.com";
//    private String testUserPassword = "secret";
//
//
//    @Autowired
//    private UserServiceImp userServiceImp;
//
//    @Autowired
//    private MockMvc mvc;
//
//    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
//            MediaType.APPLICATION_JSON.getType(),
//            MediaType.APPLICATION_JSON.getSubtype(),
//            Charset.forName("utf8"));
//
//    @Test
//    public void emailValidationRequestParamAbsent() throws Exception {
//        String url = "/email_validation";
//        this.mvc.perform(get(url)).andExpect(status().isBadRequest()).andDo(print());
//    }
//
//    @Test
//    public void emailValidationBadUuid() throws Exception {
//        userServiceImp.deleteUserByMail(testUserMail);
//        userServiceImp.create(testUserMail, testUserPassword);
//
//        String url = "/email_validation?key1=" + "nimportequoi" + "&key2=" + "nimportequoi";
//        this.mvc.perform(get(url))
//                .andExpect(status().isBadRequest()).andDo(print());
//
//        userServiceImp.deleteUserByMail(testUserMail);
//    }
//
//    @Test
//    public void emailValidationBadValidationToken() throws Exception {
//        User user = userServiceImp.create(testUserMail, testUserPassword);
//
//        String url = "/email_validation?key1=" + user.getUuid() + "&key2=" + "nimportequoi";
//        this.mvc.perform(get(url))
//                .andExpect(status().isBadRequest()).andDo(print());
//
//        userServiceImp.deleteUserByMail(testUserMail);
//
//    }
//
//    @Test
//    public void emailValidationGoodParameters() throws Exception {
//        User user = userServiceImp.create(testUserMail, testUserPassword);
//
//        String url = "/email_validation?uuid=" + user.getUuid() + "&token=" + user.getValidationToken();
//        this.mvc.perform(get(url))
//                .andExpect(status().isOk()).andDo(print());
//
//        userServiceImp.deleteUserByMail(testUserMail);
//
//    }
}
