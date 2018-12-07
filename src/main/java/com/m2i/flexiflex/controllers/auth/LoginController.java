package com.m2i.flexiflex.controllers.auth;

import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import com.m2i.flexiflex.utils.BcryptHolder;
import com.m2i.flexiflex.utils.TokenGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserServiceImp userServiceImp;

    @ApiOperation(value = "Connection de l'utilisateur", response = Object.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> login(@RequestParam(name = "email") String email, @RequestParam("password") String password) {

        if (userServiceImp.existsByMail(email)) {
            User user = userServiceImp.getByMail(email);
            if (BcryptHolder.getInstance().getbCryptPasswordEncoder().matches(password, user.getPassword()) && user.getEmailValidation()) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Email ou password incorrect.",HttpStatus.UNAUTHORIZED);

    }
}



