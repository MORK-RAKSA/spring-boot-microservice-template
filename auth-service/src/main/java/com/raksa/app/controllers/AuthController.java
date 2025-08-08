package com.raksa.app.controllers;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.JwtTokenResponseDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.services.servicesImpl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthServiceImpl authService;
    private WebClient userClient;

    @GetMapping("/get-user-info")
    public Mono<ResponseMessage<List<UserResponseDto>>> getUserInfo(@RequestHeader (value = "Authorization", defaultValue = "") String authHeader) {
        return authService.getAllUser(authHeader);
    }

    @GetMapping("/get-user-by-id")
    public Mono<ResponseMessage<UserResponseDto>> getUserById(@RequestParam String id){
        return authService.getUserById(id);
    }

    @PostMapping("/login")
    public Mono<ResponseMessage<JwtTokenResponseDto>> login(@RequestBody UserRequestDto requestDto){
        return authService.login(requestDto);
    }
}
