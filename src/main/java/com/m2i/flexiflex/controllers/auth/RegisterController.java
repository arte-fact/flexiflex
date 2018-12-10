package com.m2i.flexiflex.controllers.auth;

import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import com.m2i.flexiflex.utils.BcryptHolder;
import com.m2i.flexiflex.utils.SendMail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(value = "Controller l'inscription", description = "Opérations d'inscritpion utilisateur")
public class RegisterController {

    @Autowired
    private UserServiceImp userServiceImp;


    @ApiOperation(value = "Inscription d'un utilisateur", response = User.class, responseContainer = "List", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a enregistré"),
            @ApiResponse(code = 404, message = "Erreur")
    })
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity register(@RequestBody String email, @RequestBody String password) {
        if (!userServiceImp.existsByMail(email)) {
            try {
                User user = userServiceImp.create(
                        email,
                        BcryptHolder.getInstance().getbCryptPasswordEncoder().encode(password)
                );

                SendMail sendMail = SendMail.getInstance();
                String url="<div dir=\"ltr\"><a href=\"http://localhost:8080/auth/email_validation?key1="+ user.getUuid() + "&key2=" + user.getValidationToken() + "\">Confirmer votre inscription</a><br></div>";
                String body="Pour confirmer votre inscription, cliquez sur ce lien :" + url;
                sendMail.sendMail("flexiflex.emailvalidation@gmail.com", email, "Flexiflex - Validation de votre inscription",body);

                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
    }
}
