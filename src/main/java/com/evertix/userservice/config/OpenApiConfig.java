package com.evertix.userservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean(name = "tutofastOpenApi")
    public OpenAPI tutofastOpenApi() {
        // Swagger UI available at:
        // http://localhost:8080/swagger-ui.html
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("TutoFast User Service API Documentation").description(
                        "TutoFast API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3."));
    }
}
