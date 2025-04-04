package com.example.dynamodb_app.model;

import com.example.dynamodb_app.config.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Todo {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
