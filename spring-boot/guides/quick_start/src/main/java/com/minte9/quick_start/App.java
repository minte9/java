/**
 * Spring Boot is the most popular way to start Spring projects.
 * Spring Boot is built on top of the spring framework.
 * 
 * Creating a new Spring Boot project on start.spring.io
 * Add dependency: Spring Web
 */

package com.minte9.quick_start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
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
