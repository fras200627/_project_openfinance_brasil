package com.ofb.customers.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneOffset;

@Configuration
public class OpenAPIConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Bean(name = "ofb.openapi")
    public OpenAPI buildOpenAPIConfig() {

        //This is used as an example of using application-level values.
        System.setProperty("App.Module.Name",   buildProperties.get("project.module.name"));
        System.setProperty("App.Title",         buildProperties.get("project.title"));
        System.setProperty("App.Description",   buildProperties.get("project.description"));
        System.setProperty("App.Build.Version", buildProperties.getVersion());
        System.setProperty("App.Build.Date",    buildProperties.getTime().atOffset(ZoneOffset.ofHours(-3)).toString());
        System.setProperty("App.Contact.Name",  buildProperties.get("contact.name"));

        return new OpenAPI()
                .info(new Info()
                .title(System.getProperty("App.Module.Name"))
                .description(System.getProperty("App.Description") + "    " +
                             "(Component: " + System.getProperty("App.Title") +  " :: " +
                                             "Build version: " + System.getProperty("App.Build.Version") + " :: " +
                                             "Build date: " + System.getProperty("App.Build.Date") +
                             ")")
                .version(System.getProperty("App.Build.Version"))
                .contact(new Contact().name(System.getProperty("App.Contact.Name")))
                );
    }

}