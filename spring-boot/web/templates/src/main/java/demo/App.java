/**
 * SPRING BOOT – MVC APPLICATION
 * -----------------------------
 *
 * This class starts the Spring Boot application.
 * It launches the embedded web server and loads
 * all controllers and templates.
 *
 * Created using start.spring.io (or IDE wizard).
 *
 * Dependencies used:
 * - Spring Web 			(MVC + embedded server)
 * - Thymeleaf 				(HTML templates)
 * - Spring Boot DevTools 	(auto reload)
 *
 * Run with:
 * mvn spring-boot:run
 */

package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}