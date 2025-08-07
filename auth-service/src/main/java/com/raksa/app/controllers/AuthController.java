package com.raksa.app.controllers;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.services.servicesImpl.AuthServiceImpl;
import com.raksa.app.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthServiceImpl authService;

    @GetMapping("/getUserInfo")
    public Mono<ResponseMessage<List<UserResponseDto>>> getUserInfo(){
        return authService.getAllUser();
    }

    @GetMapping("/getUserById")
    public Mono<ResponseMessage<UserResponseDto>> getUserById(@RequestParam String id){
        return authService.getUserById(id);
    }

    @PostMapping("/login")
    public Mono<ResponseMessage<UserResponseDto>> login(@RequestBody UserRequestDto userRequestDto) {
        if(userRequestDto.getUsername().equals("admin") && userRequestDto.getPassword().equals("admin")){
            UserResponseDto userResponseDto = new UserResponseDto();
            String token = JwtUtil.generateToken(userRequestDto.getUsername());
            userResponseDto.setToken(token);
            return Mono.just(ResponseMessage.success("Login successful", userResponseDto));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
    }
}
