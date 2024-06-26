package com.minijuegos.userfunctions.controller;

import com.minijuegos.userfunctions.persistence.dto.UserDto;
import com.minijuegos.userfunctions.persistence.model.User;
import com.minijuegos.userfunctions.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userMngm;

    @Autowired
    public UserController(UserServiceImpl userMngm) {
        this.userMngm = userMngm;
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        try {
            UserDto user = userMngm.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/levelUp/{username}")
    public ResponseEntity<String> levelUp(@PathVariable String username) {
        try {
            String level = userMngm.levelUp(username);
            return new ResponseEntity<>(level, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
