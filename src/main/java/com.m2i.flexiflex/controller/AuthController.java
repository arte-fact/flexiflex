package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.model.User;
import com.m2i.flexiflex.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public AuthController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity login(@RequestParam(name = "email") String email, @RequestParam("password") String password) {

        if (userServiceImp.getByMail(email).getClass() == User.class) {
            User user = userServiceImp.getByMail(email);
            if(user.getPassword() == password) {
                return new ResponseEntity(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity("not found", HttpStatus.UNAUTHORIZED);

    }
}



