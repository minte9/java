/**
 * DemoApplication
 * ---------------
 * Entry point of the Spring Boot application.
 * 
 * What @SpringBootApplication does:
 *  - @Configuration
 *      Mark this class as a source of bean definitions
 * 
 *  - @EnableAutoConfiguration 
 *      Lets Spring Boot configure many things automatically
 *      based on dependencies in pom.xml
 *  
 *  - @ComponentScan
 *      Tells Spring to scan this package and subpackages
 *      for classes annoteted with @Component, @Service,
 *      @Repository, @Controller, @RestController, etc.
 * 
 * This class boots the entire application context.
 */

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
}
