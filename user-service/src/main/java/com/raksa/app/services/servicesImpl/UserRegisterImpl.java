package com.raksa.app.services.servicesImpl;

import com.raksa.app.clients.UserClient;
import com.raksa.app.dtos.requests.GetOTPRequestDto;
import com.raksa.app.dtos.requests.UserRegisterRequestDto;
import com.raksa.app.exception.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserRegisterImpl {

    private final WebClient webClient;

    public Mono<ResponseMessage<Object>> registerUser(UserRegisterRequestDto requestDto) {
        return webClient.post()
                .uri("/api/v1/register")
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<Object>>() {})
                .onErrorResume(e -> Mono.just(ResponseMessage.error("Fetching user registration failed: " + e.getMessage())));
    }
    public Flux<ResponseMessage<Object>> getOTP(GetOTPRequestDto requestDto) {
        return webClient.post()
                .uri("/api/v1/getOtp")
                .bodyValue(requestDto)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ResponseMessage<Object>>() {})
                .onErrorResume(e -> Flux.just(ResponseMessage.error("Fetching OTP failed: " + e.getMessage())));
    }
}
