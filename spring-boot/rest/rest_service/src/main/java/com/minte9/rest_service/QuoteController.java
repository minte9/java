/**
 * Resource Controller - GreetingController
 * 
 * HTTP requests are handled by a controller
 * Components are identified by the @RestController annotation.
 */

package com.minte9.rest_service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @GetMapping("/")
    public String quote() {
        return "Welcome! <br><br>Use /quote/{id}";
    }

    @GetMapping("/quote/{id}")
    public Quote getQuote(@PathVariable Integer id) {

        List<Quote> quotes = Arrays.asList(
            new Quote(1, "Spring Boot makes stand alone Spring apps easy."),
            new Quote(2, "With Boot you deploy everywhere.")
        );

        quotes = quotes.stream()
            .filter(x -> x.getId() == id)
            .collect(Collectors.toList());
        
        return quotes.get(0);
    }
}