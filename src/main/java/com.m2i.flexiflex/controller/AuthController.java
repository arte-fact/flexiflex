package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final Session session = HibernateSession.getSession();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> login(@RequestParam String email, @RequestParam String password) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                .add(Property.forName(UserProperties.EMAIL).eq(email) );

        try {
            UserEntity user = (UserEntity) detachedCriteria.getExecutableCriteria(session).list().get(0);

            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>((UserEntity) null, HttpStatus.UNAUTHORIZED);
    }
}



