package com.raksa.app.controllers;

import com.raksa.app.dtos.requests.GetOTPRequestDto;
import com.raksa.app.dtos.requests.UserRegisterRequestDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.services.servicesImpl.UserRegisterImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserRegisterController {
    private final UserRegisterImpl userRegister;

    @PostMapping("/v2/user-register")
    public Mono<ResponseMessage<Object>> registerUser(@RequestBody UserRegisterRequestDto requestDto){
        return userRegister.registerUser(requestDto);
    }

    @PostMapping("/v2/user-email-otp")
    public Flux<ResponseMessage<Object>> getOTP(@RequestBody GetOTPRequestDto requestDto){
        return userRegister.getOTP(requestDto);
    }

}