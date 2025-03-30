package com.hackathon.inditex.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * This class is used to configure Swagger for the application.
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    /**
     * This method is used to create an OpenAPI object that represents the Swagger API.
     *
     * @return An OpenAPI object
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion()));
    }

    /*
     * This method is used to create a GroupedOpenApi object that represents the Swagger API.
     *
     * @return A GroupedOpenApi object
     */
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/api/**")
                .packagesToScan(swaggerProperties.getBasePackage())
                .build();
    }
}
