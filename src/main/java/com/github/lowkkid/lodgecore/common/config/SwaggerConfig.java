package com.github.lowkkid.lodgecore.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    @Profile("prod")
    public OpenAPI prodOpenAPI() {
        return createOpenAPI(List.of(
                new Server().url("https://lodge-core-backend.lowkkid.dev").description("Production server")));
    }

    @Bean
    @Profile("local")
    public OpenAPI localOpenAPI() {
        return createOpenAPI(List.of(
                new Server().url("http://localhost:8080/api/v1/lodge-core").description("Local server")));
    }


    private OpenAPI createOpenAPI(List<Server> servers) {
        return new OpenAPI()
                .info(new Info()
                        .title("Lodge Core Backend API")
                        .version("1.0")
                        .description("API documentation for Lodge Core Backend"))
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .servers(servers);
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}