/**
 * Spring Boot is the most popular way to start Spring projects.
 * Spring Boot is built on top of the spring framework.
 *
 * Start by creating a new Spring Web project ... 
 * by opening intializer on start.spring.io
 */

package com.minte9.quick_start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
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
