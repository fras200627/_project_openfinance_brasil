package com.ofb.authentication;

import com.ofb.authentication.config.CustomFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableEurekaClient
public class StartApplication {
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
}
