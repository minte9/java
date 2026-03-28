/**
 * REST Api POST request
 * 
 * Request: http://localhost:8080
 * Response: http://localhost:8080/post_page
 */

package com.minte9.post_request;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String makeRequest() throws Exception {

        String url = "http://localhost:8080/post_page";
        String a   = "Hello";
        String b   = "World";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                    "a=" + URLEncoder.encode(a, "UTF-8") + "&" + 
                    "b=" + URLEncoder.encode(b, "UTF-8")))
                .build();

        HttpResponse<String> response = client.send(
            request, HttpResponse.BodyHandlers.ofString()
        );

        String out = ""
            + "Response code: " + response.statusCode() + " / "
            + "POST vars: " + response.body();

        return out; 
            // Response code: 200 / POST vars: a = Hello, b = World
    }

    @PostMapping("/post_page")
    public String handleDemoRequest(
                    @RequestParam("a") String a, 
                    @RequestParam("b") String b) {
        
        String out = "a = " + a + ", b = " + b;
        return out;
    }
}
