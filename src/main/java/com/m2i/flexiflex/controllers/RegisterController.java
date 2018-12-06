package com.m2i.flexiflex.controllers;

import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity register(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        if (!userServiceImp.existsByMail(email)) {
            try {
                User user = userServiceImp.create(email, password);
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
    }
}
