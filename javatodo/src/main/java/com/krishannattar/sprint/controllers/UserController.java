package com.krishannattar.sprint.controllers;

import com.krishannattar.sprint.models.User;
import com.krishannattar.sprint.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
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
    public ResponseEntity<?> addTodoToUser(Authentication authentication,
            @RequestBody
                    User newUserData,
            @PathVariable
                    long userid) throws URISyntaxException
    {
        User checkUser = userService.findUserById(userid);
        System.out.println(checkUser.hashCode());

        System.out.println(checkUser.getAuthority());
        System.out.println(checkUser.getAuthority().hashCode());
        System.out.println(checkUser.hashCode());
        System.out.println(checkUser.hashCode());

        System.out.println("*************");
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getAuthorities().hashCode());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getName());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.hashCode());
        userService.update(newUserData, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/users/userid/{userid}",
            produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

}
