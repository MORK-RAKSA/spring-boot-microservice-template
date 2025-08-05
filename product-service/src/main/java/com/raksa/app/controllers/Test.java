package com.raksa.app.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Test {
    @GetMapping("/test")
    public String test() {
        return "Test endpoint is working!";
    }
}
