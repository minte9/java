/**
 * IoC example (using only annotations)
 * 
 * - Enable component scanning (Configuration minimal)
 * - Mark classes as Spring-managed (@Component)
 * - Constructor injection (NotificationController)
 */

package com.example.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Main {
    public static void main(String[] args) {
        
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        NotificationController controller = 
            context.getBean(NotificationController.class);

        controller.print();  // Hello from MessageService
    }    
}

@Configuration
@ComponentScan(basePackages = "com.example.ioc")
class AppConfig {

}

@Component
class MessageService {
    public String getMessage() {
        return "Hello from MessageService";
    }
}

@Component
class NotificationController {
    private final MessageService messageService;

    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
