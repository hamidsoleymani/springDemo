package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {


    @GetMapping("/zeit")
    public String time() {
       return LocalDateTime.now().toString();
    }
}
