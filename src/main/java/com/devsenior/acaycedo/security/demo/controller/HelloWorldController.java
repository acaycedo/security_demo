package com.devsenior.acaycedo.security.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {
    @GetMapping
    public Map<String, Object> getHello(){
        return Map.of("message:","HelloWorld");
    }

    @PostMapping
    public Map<String, Object> postHello(@RequestBody Map<String, Object> body){
        return Map.of("message:","Hello "+body.get("name"));
    }
}
