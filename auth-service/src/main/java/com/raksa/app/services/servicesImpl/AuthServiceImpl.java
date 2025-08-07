package com.raksa.app.services.servicesImpl;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.JwtTokenResponseDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final WebClient userServiceWebClient;

    public Mono<ResponseMessage<List<UserResponseDto>>> getAllUser() {
        return userServiceWebClient.get()
                .uri("/get-all-users")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<List<UserResponseDto>>>() {});
    }

    public Mono<ResponseMessage<UserResponseDto>> getUserById(String id) {
        return userServiceWebClient.get()
                .uri("/get-user-by-id?id=",id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<UserResponseDto>>() {});
    }

//
//    public Mono<ResponseMessage<String>> login(UserRequestDto requestDto){
//
//        return userServiceWebClient.post()
//                .uri("/login")
//                .bodyValue(requestDto)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<String>>() {})
//                .map(response -> ResponseMessage.success("Login successful", response.getData()));
//    }
}
