package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.model.User;
import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.UserServiceImp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidationController {


    private final UserServiceImp userServiceImp;

    @Autowired
    public EmailValidationController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping(path="/email_validation", method = RequestMethod.GET)
    public ResponseEntity<String> url(@RequestParam(name = "uuid") String uuid, @RequestParam(name = "token") String validationToken) {
        try {
            Session session = HibernateSession.getSession();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class).add(Property.forName(UserProperties.UUID).eq(uuid));
            User user = (User) detachedCriteria.getExecutableCriteria(session).uniqueResult();
            System.out.println(user.toString());

            Transaction transaction = session.beginTransaction();
            user.setEmailValidation(true);
            session.save(user);
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
