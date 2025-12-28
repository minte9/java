/**
 * Maven example with Spring Boot dependency
 */

package com.minte9.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBoot.class, args); 
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}