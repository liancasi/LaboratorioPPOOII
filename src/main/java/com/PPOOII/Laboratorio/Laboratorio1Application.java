package com.PPOOII.Laboratorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class Laboratorio1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Laboratorio1Application.class, args);
	}

}
