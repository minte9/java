/**
 * Autowiring with @Component
 * 
 * Spring can scan classes and create beans automatically.
 * 
 * What Spring does behind the scene:
 *  - Scan package
 *  - Find @Component classes
 *  - Create beans
 *  - Detect constructor dependencies
 *  - Inject them automatically
 */

package com.example.bean_autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        NotificationController controller = 
            context.getBean(NotificationController.class);

        controller.print();  // Hello from Component
    }
}

// Enable component scanning

@Configuration
@ComponentScan(basePackages = "com.example.bean_autowiring")
class AppConfig {
}

//  Mark classes as beans (@Component)

@Component
class MessageService {
    public String getMessage() {
        return "Hello from Component";
    }
}

@Component
class NotificationController {

    private final MessageService messageService;

    // No @Autowired needed (Spring 4.3+)
    public NotificationController(MessageService service) {
        this.messageService = service;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
