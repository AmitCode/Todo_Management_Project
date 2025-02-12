package com.todo.management.service.serviceImpl;

import com.todo.management.dto.TodoDTOClass;
import com.todo.management.pojo.Todo;
import com.todo.management.repository.TodoRepository;
import com.todo.management.service.TodoServices;
import com.todo.management.todoMapperClass.TodoModelMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServicesImpl  implements TodoServices {
    private TodoRepository todoRepository;
    private ModelMapper modelMappers;
    TodoModelMapper modelMapper = new TodoModelMapper();
    @Override
    public TodoDTOClass addTodo(TodoDTOClass todoDTOClass){
//        Todo savedTodo = todoRepository.save(modelMapper.mapTodoToTodoDTOClass(todoDTOClass));
       Todo savedTodo = todoRepository.save(modelMappers.map(todoDTOClass, Todo.class));
        return modelMappers.map(savedTodo,TodoDTOClass.class);
        //return modelMapper.mapTodoDtoToTodoClass(savedTodo);
    }

    @Override
    public TodoDTOClass getTodo(Long id){
        Todo todo = todoRepository.findById(id).get();
        return modelMappers.map(todo,TodoDTOClass.class);
    }


    @Override
    public List<TodoDTOClass> getAllTodos(){
        List<Todo> todoList =  todoRepository.findAll();
        //List<TodoDTOClass> listOfTodo = new ArrayList<>();
        return todoList.stream().map((todo) -> modelMappers.map(todo,TodoDTOClass.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDTOClass markTodoAsComplete(Long id){
        Todo todo = todoRepository.findById(id).get();
        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMappers.map(updatedTodo,TodoDTOClass.class);
    }

    @Override
    public List<TodoDTOClass> getTodosBasedOnComplete(boolean isCompleted){
        System.out.println("IS Complete: "+isCompleted);
        List<Todo> todoList =  todoRepository.findByIsCompleted(isCompleted);
        List<TodoDTOClass> listOfTodo = new ArrayList<>();
        return todoList.stream().map(todo -> modelMappers.map(todo,TodoDTOClass.class)).collect(Collectors.toList());
        //return listOfTodo;
    }
}
