package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.model.User;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.SendMail;
import com.m2i.flexiflex.service.UserServiceImp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public RegisterController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestParam(name = "email", value = "email") String email, @RequestParam(name = "password") String password) {
        final Session session = HibernateSession.getSession();

        try {
            if (userServiceImp.getByMail(email).getClass() == User.class){
                User user = userServiceImp.getByMail(email);
                user.setEmailValidation(true);
                user = userServiceImp.update(user);

                //ENVOI MAIL VALIDATION
                SendMail send_mail    =   new SendMail();
                String url="<div dir=\"ltr\"><a href=\"http://localhost:8080/email_validation?key1="+ user.getUuid() + "&key2=" + user.getValidationToken() + "\">Confirmer votre inscription</a><br></div>";
                String body="Pour confirmer votre inscription, cliquez sur ce lien :" + url;


//                send_mail.sendMail("flexiflex.emailvalidation@gmail.com", "florent.chazot@gmail.com", "Flexiflex - Validation de votre inscription",body);

                return new ResponseEntity(url, HttpStatus.CREATED);
            }

            else {
                return new ResponseEntity("User aleady exist... ", HttpStatus.BAD_REQUEST);

            }
        } catch (HibernateException e) {
            e.fillInStackTrace();
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
