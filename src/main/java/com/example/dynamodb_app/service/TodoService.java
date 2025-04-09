package com.example.dynamodb_app.service;

import com.example.dynamodb_app.model.Todo;
import com.example.dynamodb_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            todo.setTitle(todoDetails.getTitle());
            todo.setDescription(todoDetails.getDescription());
            todo.setCompleted(todoDetails.isCompleted());
            todo.setUpdatedAt(LocalDateTime.now());
            return todoRepository.save(todo);
        }
        return null;
    }

    public boolean deleteTodo(String id) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
