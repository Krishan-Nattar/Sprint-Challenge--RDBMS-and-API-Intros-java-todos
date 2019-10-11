package com.krishannattar.sprint.controllers;

import com.krishannattar.sprint.models.User;
import com.krishannattar.sprint.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getUsername(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/users/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    {
//        "todos":
//        [
//        {
//            "description": "Have Fun",
//                "datestarted": "2019-01-01T01:00"
//        }
//        ]
//    }
    @PutMapping(value = "/users/todo/{userid}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(
            @RequestBody
                    User newUserData,
            @PathVariable
                    long userid) throws URISyntaxException
    {
        userService.update(newUserData, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
