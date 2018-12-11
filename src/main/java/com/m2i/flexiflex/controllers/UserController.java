package com.m2i.flexiflex.controllers;

import com.m2i.flexiflex.http.responses.UserResponse;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @ApiOperation(value = "Recupération des données utilistateur", response = Object.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> login(@RequestHeader String uuid, @RequestHeader String token) {

        if (userServiceImp.existsByUUID(uuid)) {
            User user = userServiceImp.getByUUID(uuid);
            if(user.getValidationToken().equals(token) && user.getEmailValidation()) {
                return new ResponseEntity<>(new UserResponse(user.getEmail()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Email ou password incorrect.", HttpStatus.UNAUTHORIZED);
    }
}