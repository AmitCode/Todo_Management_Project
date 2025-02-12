package com.todo.management.repository;

import com.todo.management.pojo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    public List<Todo> findByIsCompleted(boolean isCompleted);
}
