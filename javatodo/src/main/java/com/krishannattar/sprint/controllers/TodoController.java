package com.krishannattar.sprint.controllers;

import com.krishannattar.sprint.models.Todo;
import com.krishannattar.sprint.models.User;
import com.krishannattar.sprint.services.TodoService;
import com.krishannattar.sprint.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;


    @Autowired
    private UserService userService;



//    {
//        "completed": true
//    }

    @PutMapping(value = "/todos/todoid/{todoid}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(Authentication authentication, @RequestBody Todo updatedTodo, @PathVariable long todoid)
    {
        User currentUser = userService.findUserByName(authentication.getName());
        for(Todo t: currentUser.getTodos())
        {
            if(t.getTodoid()==todoid)
            {
                todoService.update(updatedTodo, todoid);
                return new ResponseEntity<>("UPDATE SUCCESS", HttpStatus.OK);
            }
        }
//        Todo newTodo = todoService.findTodoByTodoid(todoid);


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
