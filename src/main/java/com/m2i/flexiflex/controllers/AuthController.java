package com.m2i.flexiflex.controllers;

import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
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
public class AuthController {

    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> login(@RequestParam(name = "email") String email, @RequestParam("password") String password) {

        if (userServiceImp.getByMail(email) != null) {
            User user = (User) userServiceImp.getByMail(email);
            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
    @ApiOperation(value = "Retourne la liste des utilisateurs", response = User.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public List<User> getAll() {
        return userServiceImp.list();
    }

}



