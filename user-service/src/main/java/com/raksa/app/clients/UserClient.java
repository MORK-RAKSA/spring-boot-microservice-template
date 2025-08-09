package com.raksa.app.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserClient {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://duck-smoking-stage.loca.lt")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
