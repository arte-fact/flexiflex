package com.m2i.flexiflex.controllers.auth;

import com.m2i.flexiflex.http.requests.LoginRequest;
import com.m2i.flexiflex.http.responses.LoginResponse;
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
public class LoginController {

    @Autowired
    private UserServiceImp userServiceImp;

    @ApiOperation(value = "Connection de l'utilisateur", response = Object.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> login(@RequestBody String jsonRequest) throws JSONException {
        LoginRequest request = LoginRequest.buildFromJson(jsonRequest);

        if (userServiceImp.existsByMail(request.email)) {

            User user = userServiceImp.getByMail(request.email);

            if (BcryptHolder.getInstance().getbCryptPasswordEncoder().matches(request.password, user.getPassword())
                    && user.getEmailValidation()) {

                User newUser = userServiceImp.updateValidationToken(user);
                LoginResponse response = new LoginResponse(newUser.getUuid(), user.getValidationToken());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Email ou password incorrect.",HttpStatus.UNAUTHORIZED);
    }
}