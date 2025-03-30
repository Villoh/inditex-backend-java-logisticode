package com.hackathon.inditex.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger properties configuration.
 */
@Component
@Data
@ConfigurationProperties(prefix = "springdoc.info")
public class SwaggerProperties {
    private String title;
    private String description;
    private String version;
    private String basePackage;
}
