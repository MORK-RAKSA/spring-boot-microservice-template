package com.raksa.app.controllers;


import com.raksa.app.exception.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class Test {

//    @GetMapping("/test")
//    public Mono<String> test() {
//        return Mono.just("Hello, World! from Product Testing Service");
//    }

    @GetMapping("/test")
    public ResponseMessage<String> test() {
        return ResponseMessage.success("Hello, World! from Product Testing Service");
    }
}
