package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidationController {
    @RequestMapping(path="/email_validation", method = RequestMethod.GET)
    public ResponseEntity<String> url(@RequestParam(name = "uuid") String uuid, @RequestParam(name = "token") String validationToken) {
        try {
            Session session = HibernateSession.getSession();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class).add(Property.forName(UserProperties.UUID).eq(uuid));
            UserEntity userEntity = (UserEntity) detachedCriteria.getExecutableCriteria(session).uniqueResult();
            System.out.println(userEntity.toString());

            Transaction transaction = session.beginTransaction();
            userEntity.setEmailValidation(true);
            session.save(userEntity);
            transaction.commit();
            return new ResponseEntity<>("YEAH", HttpStatus.OK);
        }
        catch (HibernateException e){
            System.out.println(">>>>>>>>>>>>>>>>>>> 2:"+ e);
        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Fait chier", HttpStatus.OK);
    }
}
