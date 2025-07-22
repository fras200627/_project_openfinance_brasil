package com.ofb.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAPIConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}