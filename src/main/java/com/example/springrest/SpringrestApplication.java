package com.example.springrest;

import exception.CustomExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CustomExceptionHandler.class)
public class SpringrestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
	}

}
