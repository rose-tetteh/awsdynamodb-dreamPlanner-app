package com.example.dynamodb_app.repository;


import com.example.dynamodb_app.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TodoRepository {
    private final DynamoDbTable<Todo> todoTable;

    @Autowired
    public TodoRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.todoTable = dynamoDbEnhancedClient.table("Todos", TableSchema.fromBean(Todo.class));
    }

    public List<Todo> findAll() {
        PageIterable<Todo> results = todoTable.scan();
        List<Todo> todos = new ArrayList<>();
        results.items().forEach(todos::add);
        return todos;
    }

    public Todo findById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        return todoTable.getItem(key);
    }

    public Todo save(Todo todo) {
        if (todo.getId() == null || todo.getId().isEmpty()) {
            todo.setId(UUID.randomUUID().toString());
        }
        todoTable.putItem(todo);
        return todo;
    }

    public void deleteById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        todoTable.deleteItem(key);
    }
}