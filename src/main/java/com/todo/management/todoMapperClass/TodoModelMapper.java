package com.todo.management.todoMapperClass;

import com.todo.management.dto.TodoDTOClass;
import com.todo.management.pojo.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoModelMapper {
    public Todo mapTodoToTodoDTOClass(TodoDTOClass todoDTOClass){
        Todo todo = new Todo();
        todo.setTitle(todoDTOClass.getTitle());
        todo.setDescription(todoDTOClass.getDescription());
        todo.setCompleted(todoDTOClass.isCompleted());
        return todo;
    }

    public TodoDTOClass mapTodoDtoToTodoClass(Todo todo){
        TodoDTOClass todoDTOClass =new TodoDTOClass();
        todoDTOClass.setId(todo.getId());
        todoDTOClass.setTitle(todo.getTitle());
        todoDTOClass.setDescription(todo.getDescription());
        todoDTOClass.setCompleted(todo.isCompleted());
        return todoDTOClass;
    }
}
