package com.tican.lib.interceptor.config;

import com.tican.lib.interceptor.GenericInterceptorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration @Slf4j
public class AppWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GenericInterceptorHandler());
        log.info("Tican Add GenericInterceptorHandler executed successfully");
    }

}
