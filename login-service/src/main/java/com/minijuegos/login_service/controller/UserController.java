package com.minijuegos.login_service.controller;

import com.minijuegos.login_service.persistence.dto.UserDto;
import com.minijuegos.login_service.persistence.model.UserRequest;
import com.minijuegos.login_service.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userMngm;

    @Autowired
    public UserController(UserServiceImpl userMngm) {
        this.userMngm = userMngm;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserRequest userRequest) {
        try {
            UserDto loggedInUser = userMngm.login(userRequest);
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRequest userRequest) {
        try {
            UserDto registeredUser = userMngm.register(userRequest);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
