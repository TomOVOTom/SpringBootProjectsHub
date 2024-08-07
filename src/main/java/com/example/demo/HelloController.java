package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/hello")
    public String postHello(@RequestBody String message) {
        return "Received: " + message;
    }

    @GetMapping("/hello/{name}")
    public String helloWithName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @PutMapping("/hello")
    public String putHello(@RequestBody String message) {
        return "Updated: " + message;
    }

    @DeleteMapping("/hello/{name}")
    public String deleteHello(@PathVariable String name) {
        return "Deleted: " + name;
    }
}