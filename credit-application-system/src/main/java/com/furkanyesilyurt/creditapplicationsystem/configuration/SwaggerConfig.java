package com.furkanyesilyurt.creditapplicationsystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("banking-api")
                .pathsToMatch("/furkanyesilyurt/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact();
        contact.email("f.yesilyurt@outlook.com");
        contact.name("Furkan Yesilyurt");

        return new OpenAPI()
                .info(new Info()
                        .title("Banking API")
                        .description("Credit Application System | This API is developed for n11 Java Bootcamp Graduate Homework.")
                        .version("1.0")
                        .contact(contact)
                );
    }

}