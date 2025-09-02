package com.ofb.authorization.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFilterConfig {
    @Bean
    public FilterRegistrationBean<CustomFilter> appCustomFilterRegistration() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.setOrder(-2);
        return registrationBean;
    }
}
