package com.mahajan.Expenseker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpensekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensekerApplication.class, args);
		System.out.println("expenseker app started");
	}
}
