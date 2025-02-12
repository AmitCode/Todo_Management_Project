package com.todo.management.service;

import com.todo.management.dto.TodoDTOClass;

import java.util.List;

public interface TodoServices {
    public TodoDTOClass addTodo(TodoDTOClass todoDTOClass);
    public TodoDTOClass getTodo(Long id);
    public List<TodoDTOClass> getAllTodos();
    public TodoDTOClass markTodoAsComplete(Long id);
    public List<TodoDTOClass> getTodosBasedOnComplete(boolean isCompleted);
}
