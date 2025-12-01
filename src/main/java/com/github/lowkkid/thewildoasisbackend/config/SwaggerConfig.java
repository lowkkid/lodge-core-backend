package com.github.lowkkid.thewildoasisbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.webmvc.api.OpenApiResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    @Profile("prod")
    public OpenAPI prodOpenAPI() {
        return createOpenAPI(List.of(
                new Server().url("https://the-wild-oasis-backend.lowkkid.dev").description("Production server")));
    }

    @Bean
    @Profile("local")
    public OpenAPI localOpenAPI() {
        return createOpenAPI(List.of(
                new Server().url("http://localhost:8080/api/v1/the-wild-oasis").description("Local server")));
    }


    private OpenAPI createOpenAPI(List<Server> servers) {
        return new OpenAPI()
                .info(new Info()
                        .title("The Wild Oasis Backend API")
                        .version("1.0")
                        .description("API documentation for The Wild Oasis Backend"))
                .servers(servers);
    }
}