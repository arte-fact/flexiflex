package com.m2i.flexiflex.controllers.auth;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidationController {


    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    public EmailValidationController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping(path="auth/email_validation", method = RequestMethod.GET)
    public ResponseEntity<Object> url(@RequestParam(name = "key1") String uuid, @RequestParam(name = "key2") String validationToken) {

        if (userServiceImp.existsByUUID(uuid)) {
            User user = userServiceImp.getByUUID(uuid);
            if (user.getValidationToken().equals(validationToken)) {
                user.setEmailValidation(true);
                userServiceImp.update(user);
                return new ResponseEntity<>(
                        "<h1> Votre compte à été validé, félicitations!<h1><a href=\"http://flexiflex.tk\">flexiflex.tk</a>",
                        HttpStatus.OK
                );
            }
        }
        return new ResponseEntity<>("Oups! Erreur...", HttpStatus.UNAUTHORIZED);
    }
}
