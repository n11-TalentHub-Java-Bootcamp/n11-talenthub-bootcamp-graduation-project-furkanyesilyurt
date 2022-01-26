package com.furkanyesilyurt.creditapplicationsystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
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
    public OpenAPI customOpenAPI(@Value("Credit Application System") String description, @Value("1.0") String version) {
        return new OpenAPI()
                .info(new Info()
                        .title("Banking API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Banking API licence")));
    }

}