package com.raksa.app.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class UserServiceClient {

    @Bean
    public WebClient userServiceWebClient(HttpClient httpClient) {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/api-app/v1.0.0/user-service")
                .defaultHeader("Accept", "application/json")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
