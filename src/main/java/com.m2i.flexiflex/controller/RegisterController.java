package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.persistence.UserPersistence;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.SendMail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestParam(name = "email", value = "email") String email, @RequestParam(name = "password") String password) {
        final Session session = HibernateSession.getSession();

        UserEntity user = new UserEntity();
        try {
            if (!UserPersistence.exist(email)){
                UserPersistence.create(email, password);

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
