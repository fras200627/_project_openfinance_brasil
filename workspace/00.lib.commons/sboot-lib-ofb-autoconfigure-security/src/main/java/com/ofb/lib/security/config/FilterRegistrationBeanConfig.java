package com.ofb.lib.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Slf4j
public class FilterRegistrationBeanConfig {

    /* **********************************************************************************
    NOTE about not using @Component and configuring the class with @Order(2):
    The APIs create a @Bean in the 'StartApplication' class to safely
    inject java.servlet.doFilter through the 'FilterRegistrationBean'.

    The 'AppServetFilterConfig' class is injected by the
    LIB dependency 'ofb-lib-autoconfigure-service'
    *********************************************************************************** */
    @Bean
    public FilterRegistrationBean<AppServetFilterConfig> loggingFilter(){
        FilterRegistrationBean<AppServetFilterConfig> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AppServetFilterConfig());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
