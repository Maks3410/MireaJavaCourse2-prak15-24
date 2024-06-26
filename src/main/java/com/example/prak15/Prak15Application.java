package com.example.prak15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Prak15Application {
	public static void main(String[] args) {
		SpringApplication.run(Prak15Application.class, args);
	}
}
