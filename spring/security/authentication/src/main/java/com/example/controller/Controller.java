package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public access";
    }

    /**
     * Private endpoint.
     * 
     * Require username/password.
     */
    @GetMapping("/private")
    public String privateEndpoint() {
        return "Private access";
    }
}
