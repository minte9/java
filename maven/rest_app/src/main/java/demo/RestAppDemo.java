/**
 * SPRING BOOT - REST APPLICATION DEMO
 * -----------------------------------
 * This example demostrates a minimal Spring Boot application
 * built with Maven.
 * 
 * WHAT SPRING BOOT PROVIDES:
 * --------------------------
 *  - Auto-configuration (no XML)
 *  - Embedded web server (Tomcat by default)
 *  - Executable JAR out of the box
 *  - Sensible defaults for web applications
 * 
 * WHAT THIS APPLICATION DOES:
 * ---------------------------
 *  - Starts the embedded HTTP server
 *  - Exposes two REST endpoints:
 *      GET /       - "Welcome"
 *      GET /hello  - "Hello World"
 * 
 * HOW IT IS RUN:
 * --------------
 *  - mvn spring-boot:run
 *  - OR: java -jar target/app.jar
 * 
 * TEST THE APPLICATION:
 * ---------------------
 * If the port 8080 is already in use, create a properties file:
 * resources/application.properties
 *   - server.port=8081
 * 
 * curl http://localhost:8081
 * curl http://localhost:8081/hello
 */

package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication          // Enable auto-configuration + component scanning
@RestController                 // Marks this class as a REST controller
public class RestAppDemo {

    public static void main(String[] args) {
        SpringApplication.run(RestAppDemo.class, args); 
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