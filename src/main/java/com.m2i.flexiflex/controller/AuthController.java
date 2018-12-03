package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.persistence.UserPersistence;
import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam(name = "email") String email, @RequestParam("password") String password) {

        if (UserPersistence.exist(email)) {
            if (UserPersistence.getByEmail(email).getPassword() == password) {
                return new ResponseEntity(UserPersistence.getByEmail(email), HttpStatus.OK);
            }
        }
        return new ResponseEntity("not found", HttpStatus.UNAUTHORIZED);

    }
}



