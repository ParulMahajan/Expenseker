package com.mahajan.Expenseker;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class ExpensekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensekerApplication.class, args);
	}
	

}
