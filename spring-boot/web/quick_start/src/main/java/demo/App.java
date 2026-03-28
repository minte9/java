/**
 * SPRING BOOT – QUICK START
 * -------------------------
 *
 * What is Spring Boot?
 * --------------------
 * Spring Boot is an framework built on top of Spring.
 * Its goal is to let you create production-ready applications
 * with minimal configuration.
 *
 * It follows the principle:
 *   "Convention over Configuration" 
 *
 * This means:
 * - Sensible defaults are provided automatically
 * - You configure only what you want to change
 * - No XML configuration is required
 * 
 * Project creation
 * ----------------
 * A Spring Boot project is usually created on:
 *   https://start.spring.io
 * 
 * Minimum dependency needed for a REST app:
 *  - Spring Web
 *
 * What Spring Boot does for us here:
 * ----------------------------------
 * - Starts an embedded web server (Tomcat by default)
 * - Auto-configures Spring MVC
 * - Scans this package for components
 * - Exposes HTTP endpoints with annotations
 *
 * Annotations:
 * ------------
 * 1) SpringBootApplication
 *      - Marks this as the main application class
 *      - Enables auto-configuration and component scanning
 *
 * 2) RestController
 *      - Marks this class as a REST controller
 *      - Methods return data directly (no views)
 *
 * 3) GetMapping
 *      - Maps HTTP GET requests to Java methods
 *
 * Configuration by convention
 * ----------------------------
 * Default server port: 8080
 * Can be overridden in:
 *   src/main/resources/application.properties
 *
 * Example:
 *   server.port=9090
 *
 * How to run
 * ----------
 * - mvn spring-boot:run
 * - OR build and run:
 *     mvn package
 *     java -jar target/quick_start.jar 
 *
 * Test URLs
 * ---------
 * - http://localhost:9090/
 * - http://localhost:9090/hello
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
