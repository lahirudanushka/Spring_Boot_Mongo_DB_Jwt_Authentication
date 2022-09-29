package com.demo.jwtdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    ResponseEntity<String> home(){
        return ResponseEntity.ok().body("Welcome to Home");
    }
}
