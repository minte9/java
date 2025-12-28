/**
 * Standard Groovy includes a Grab annotation to declare dependencies 
 * With RestController annotation Spring Boot grabs Tomcat and Spring Mvc
 */

@RestController
class App {

    @RequestMapping("/") 
    String home() {
        "Hello Groovy"
    }
}