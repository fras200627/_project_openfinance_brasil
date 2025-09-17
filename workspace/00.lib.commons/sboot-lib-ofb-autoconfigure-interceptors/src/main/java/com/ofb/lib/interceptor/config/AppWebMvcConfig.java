package com.ofb.lib.interceptor.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofb.lib.interceptor.GenericInterceptorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration @Slf4j
public class AppWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GenericInterceptorHandler());
        log.info("OFB Add GenericInterceptorHandler executed successfully");
    }

    @Bean @Primary @JsonInclude(JsonInclude.Include.NON_NULL)
    public Jackson2ObjectMapperBuilderCustomizer getObjectMapper() {
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL);
    }

//    @Bean @Primary
//    public Jackson2ObjectMapperBuilderCustomizer getObjectMapper() {
//        Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer = new Jackson2ObjectMapperBuilderCustomizer() {
//            @Override
//            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//                Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL);
////                        .serializationInclusion(JsonInclude.Include.NON_EMPTY)
////                        .serializationInclusion(JsonInclude.Include.NON_ABSENT
////                        );
//            }
//        };
//        return jackson2ObjectMapperBuilderCustomizer;
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
