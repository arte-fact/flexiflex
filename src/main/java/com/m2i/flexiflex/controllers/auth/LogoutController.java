package com.m2i.flexiflex.controllers.auth;

import com.m2i.flexiflex.http.requests.LoginRequest;
import com.m2i.flexiflex.http.responses.LoginResponse;
import com.m2i.flexiflex.http.responses.UserResponse;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import com.m2i.flexiflex.utils.BcryptHolder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LogoutController {

    @Autowired
    private UserServiceImp userServiceImp;

    @ApiOperation(value = "Connection de l'utilisateur", response = Object.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/auth/logout", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> logout(@RequestHeader String uuid, @RequestHeader String token) {

        if (userServiceImp.existsByUUID(uuid)) {
            User user = userServiceImp.getByUUID(uuid);
            if(user.getValidationToken().equals(token) && user.getEmailValidation()) {
                user.setValidationToken("undefined");
                userServiceImp.update(user);
            }
        }
        return new ResponseEntity<>("Email ou password incorrect.", HttpStatus.UNAUTHORIZED);
    }
}