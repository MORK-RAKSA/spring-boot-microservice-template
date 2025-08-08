package com.raksa.app.controllers;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.userDto.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.services.servicesImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/create-user")
    public ResponseMessage<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = userService.createUser(requestDto);
        return ResponseMessage.success("User Created Successfully.", responseDto);
    }

    @GetMapping("/get-all-users")
    public ResponseMessage<List<UserResponseDto>> getAllUsers() {
        return ResponseMessage.success("Fetch Users Successfully.", userService.getAllUser());
    }

    @PostMapping("/delete-all-users")
    public ResponseMessage<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseMessage.success("All Users Deleted Successfully.");
    }

    @PostMapping("/delete-all-users1")
    public ResponseMessage<Void> deleteAllUsers1() {
        userService.deleteAll();
        return ResponseMessage.success("All Users Deleted Successfully.");
    }

    @GetMapping("/get-user-by-id")
    public ResponseMessage<UserResponseDto> getUserById(@RequestParam String id) {
        UserResponseDto responseDto = userService.getUserById(id);
        return ResponseMessage.success("Fetch User Successfully.", responseDto);
    }

    @GetMapping("/get-user-by-username")
    public ResponseMessage<UserResponseDto> getUserByUsername(@RequestParam String username) {
        UserResponseDto responseDto = userService.getUserByUsername(username);
        return ResponseMessage.success("Fetch User Successfully.", responseDto);
    }

    @GetMapping("/get-product-testing")
    public Mono<ResponseMessage<String>> getProductTesting() {
        return userService.getProductTesting();
    }
}