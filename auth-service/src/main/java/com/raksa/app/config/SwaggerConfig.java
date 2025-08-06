package com.raksa.app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server gatewayServer = new Server();
        gatewayServer.setUrl("/api-app/v1.0.0/auth-service/");

        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Use the JWT token for authentication.")))
                .addServersItem(gatewayServer)
                .info(new Info()
                        .title("Pretty little baby")
                        .version("1.0.0"));
    }

    @Bean
    public RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources("/**", new ClassPathResource("static/"));
    }
}
