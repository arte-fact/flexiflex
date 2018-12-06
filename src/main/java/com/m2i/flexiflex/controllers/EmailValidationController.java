package com.m2i.flexiflex.controllers;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import org.hibernate.HibernateException;
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

            return new ResponseEntity<>("YEAH", HttpStatus.OK);
        }
        catch (HibernateException e){
            System.out.println(">>>>>>>>>>>>>>>>>>> 2:"+ e);
        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Fait chier", HttpStatus.OK);
    }
}
