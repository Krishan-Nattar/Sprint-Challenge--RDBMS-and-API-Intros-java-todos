package com.krishannattar.sprint.services;

import com.krishannattar.sprint.models.Todo;
import com.krishannattar.sprint.repos.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "todoService")
public class TodoServiceImplementation implements TodoService{
    @Autowired
    private ToDoRepository todorepos;

    @Override
    public Todo update(Todo todo, long id) {
        System.out.println("update todo");
        Todo currentTodo = todorepos.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Long.toString(id)));
        System.out.println("todo found");
        if(todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }
        if(todo.getDatestarted() != null)
        {
            currentTodo.setDatestarted(todo.getDatestarted());
        }
        if(todo.isCompleted() != false)
        {
            currentTodo.setCompleted(true);
        }
        else {
            currentTodo.setCompleted(false);
        }
        if(todo.getUser() != null)
        {
            currentTodo.setUser(todo.getUser());
        }
        return todorepos.save(currentTodo);
    }
}
