/**
 * Consuming REST App
 * 
 * Add mapping (/getquote) from a separate service on 8080 port
 * Use a domain class (Quote) to contain the data that you need.
 * Any property not bound will be ignored.
 * 
 * RestTemplate will use the Jackson JSON library for incomming data.
 * CommandLineRunner will run the RestTemplate on startup.
 */

package com.minte9.consuming_rest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "REST Api Client <br><br>Use /request_quote";
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/request_quote")
    public Object quote() {
        RestTemplate rt = new RestTemplate(); // Browser

        Object quote = rt.getForObject(
            "http://localhost:9090/quote/1", Object.class
        );
        return quote; // {"id":1,"content":"Spring Boot makes ...
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) // Console
        throws Exception {

            return args -> {
                Object quote = restTemplate.getForObject(
                    "http://localhost:9090/quote/2", Object.class
                );

                System.out.println(quote); // {"id":2,"content":"With Boot ...
            };
    }
}
