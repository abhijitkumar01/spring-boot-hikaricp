package com.example.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot, I use HikariCP!";
  }
}
