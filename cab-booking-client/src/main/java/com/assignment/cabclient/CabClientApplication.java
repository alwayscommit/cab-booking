package com.assignment.cabclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.assignment.cabclient")
public class CabClientApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CabClientApplication.class, args);
	}

}
