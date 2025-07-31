package com.raksa.app.controllers;

import com.raksa.app.exception.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-app/v1.0.0/test-service")
public class TestController {
    @GetMapping("/info")
    public ResponseMessage<String> testInfo(){
        return ResponseMessage.success("Test Get Controller" );
    }
}
