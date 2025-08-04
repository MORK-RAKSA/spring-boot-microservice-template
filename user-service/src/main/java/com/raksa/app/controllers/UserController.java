package com.raksa.app.controllers;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.services.servicesImpl.UserServiceIpml;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserServiceIpml userService;

    @PostMapping("/create-users")
    public ResponseMessage<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = userService.createUser(requestDto);
        return ResponseMessage.success("User Created Successfully", responseDto);
    }
}
