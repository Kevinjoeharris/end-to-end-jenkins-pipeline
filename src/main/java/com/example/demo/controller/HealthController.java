package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    // Simple ping endpoint
    @GetMapping("/api/info")
    public Map<String, String> root() {
        return Map.of(
            "app",     "spring-cicd-demo",
            "version", "1.0.0",
            "status",  "running"
        );
    }
}
