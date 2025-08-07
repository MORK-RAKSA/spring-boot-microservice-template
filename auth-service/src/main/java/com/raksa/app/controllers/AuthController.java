package com.raksa.app.controllers;

import com.raksa.app.client.UserServiceClient;
import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.JwtTokenResponseDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.services.servicesImpl.AuthServiceImpl;
import com.raksa.app.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthServiceImpl authService;
    private WebClient userClient;

    @GetMapping("/get-user-info")
    public Mono<ResponseMessage<List<UserResponseDto>>> getUserInfo() {
        return authService.getAllUser();
    }

    @GetMapping("/get-user-by-id")
    public Mono<ResponseMessage<UserResponseDto>> getUserById(@RequestParam String id){
        return authService.getUserById(id);
    }

    @PostMapping("/login")
    public Mono<ResponseMessage<JwtTokenResponseDto>> login(@RequestBody UserRequestDto userRequestDto) {
        if(userRequestDto.getUsername().equals("string") && userRequestDto.getPassword().equals("string")){
            JwtTokenResponseDto jwtTokenResponseDto = new JwtTokenResponseDto();
            String token = JwtUtil.generateToken(userRequestDto.getUsername());
            jwtTokenResponseDto.setToken(token);
            return Mono.just(ResponseMessage.success("Login successful", jwtTokenResponseDto));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
    }

//    @PostMapping("/login")
//    public Mono<ResponseMessage<String>> login(@RequestBody UserRequestDto requestDto) {
//        return authService.login(requestDto);
//    }

}
