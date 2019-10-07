package com.userorgmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.userorgmanagement.repository"})
@EntityScan(basePackages = "com.userorgmanagement.model")
@EnableTransactionManagement
@EnableWebMvc
public class ManagementSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementSpringApplication.class, args);
	}

}
