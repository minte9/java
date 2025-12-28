/**
 * Rest Controller service
 * 
 * Use start.spring.io to create a new Spring Web project. 
 * The service will handle GET requests for /greeting and return JSON response. 
 * 
 * Use start.spring.io to create a new Spring Web project.
 */

package com.minte9.rest_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}