package com.example.dynamodb_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.dynamodb_app"})
public class DynamodbAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamodbAppApplication.class, args);
	}

}
