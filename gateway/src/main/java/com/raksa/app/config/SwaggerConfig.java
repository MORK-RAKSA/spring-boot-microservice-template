package com.raksa.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Server httpsServer = new Server();
        httpsServer.setUrl("http://localhost:8080/");

        return new OpenAPI()
                .addServersItem(httpsServer)
                .info(new Info().title("Pretty little baby").version("1.0.0"));
    }
}
