package com.hos;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hos.controller.MemberController;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.hos.domain"})
@ComponentScan("com.hos.controller")
@EnableTransactionManagement
@EnableJpaRepositories

@SpringBootApplication
public class HosApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(HosApplication.class, args);
	}

}

