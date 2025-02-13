package com.todo.management.controller;

import com.todo.management.dto.TodoDTOClass;
import com.todo.management.service.TodoServices;
import com.todo.management.service.serviceImpl.TodoServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/todoServices")
public class TodoController {

    private TodoServicesImpl services;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createTodo")
    public ResponseEntity<TodoDTOClass> createTodo(@RequestBody TodoDTOClass todoDTOClass){
        return new ResponseEntity<>(services.addTodo(todoDTOClass), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getTodoById")
    public ResponseEntity<TodoDTOClass> getTodoById(@RequestHeader Long id){
        return new ResponseEntity<>(services.getTodo(id),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getAllTodos")
    public ResponseEntity<List<TodoDTOClass>> getAllTodos(){
        return new ResponseEntity<>(services.getAllTodos(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/markTodoAsComplete")
    public ResponseEntity<TodoDTOClass> markTodoAsComplete(@RequestHeader Long id){
        return new ResponseEntity<>(services.markTodoAsComplete(id),HttpStatus.ACCEPTED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getComleteIncompletedTodos")
    public ResponseEntity<List<TodoDTOClass>> getComletedIncompletedTodos(@RequestHeader  boolean isCompleted){
        return new ResponseEntity<>(services.getTodosBasedOnComplete(isCompleted),HttpStatus.OK);

    }
}
