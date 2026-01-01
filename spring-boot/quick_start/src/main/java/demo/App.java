/**
 * SPRING BOOT - QUICK START APPLICATION
 * -------------------------------------
 * This is the main entry point of a Spring Boot application.
 * It starts the embedded web server (Tomcat by default) 
 * and defines simple HTTP endpoints.
 * 
 * START.SPRING.IO
 * ---------------
 * Spring Boot is the most popular way to start Spring projects.
 * It is built on top of the spring framework.
 * 
 * We can create a new project on start.spring.io and
 * add one dependency (Spring Web).
 * 
 * KEY CONCEPTS:
 * ------------
 * 1) @SpringBootApplicaton
 *  - Marks this class as the main Spring Boot configuration.
 *  - Enables:
 *      - Auto-configuration
 *      - Component scanning
 *      - Spring Boot defaults
 * 
 * 2) Embedde Server
 *  - No external Tomcat installation is required.
 *  - The application runs as a standalone JAR.
 * 
 * 3) @RestController
 *  - Combines @Controller + @ResponseBody
 *  - Every method return data directly (not a view here).
 * 
 * 4) @GetMapping
 *  - Maps HTTP GET requests to Java methods.
 * 
 * HOW TO RUN:
 * -----------
 * - mvn spring-boot:run
 * - OR package the app and run:
 *     java -jar target/quick_start.jar
 * 
 * URLs:
 * -----
 * - http://localhost:8080/
 * - http://localhost:8080/hello
 */

package demo;

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
