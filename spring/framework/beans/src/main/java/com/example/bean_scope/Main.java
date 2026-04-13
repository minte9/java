/**
 * 1. Bean Scope - Singleton (DEFAULT)
 * 
 * Concept:
 *  - one instance  per Spring container
 *  - created when context starts
 *  - reused everywhere
 * 
 * 2. Bean Scope - Prototype
 * 
 * Concept:
 *  - new instance every time requested
 *  - Spring creates it, but does NOT manage full lifecycle
 *
 */

package com.example.bean_scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        
        MyService s1 = context.getBean(MyService.class);
        MyService s2 = context.getBean(MyService.class);

        // same instance
        System.out.println(s1 == s2);  // true

        MyEvent e1 = context.getBean(MyEvent.class);
        MyEvent e2 = context.getBean(MyEvent.class);

        // different instances
        System.out.println(e1 == e2);  // false
    }
}

@Configuration
class AppConfig {

    @Bean
    MyService myService() {
        return new MyService();
    }

    @Bean
    @Scope("prototype")  // Look Here
    MyEvent myEvent() {
        return new MyEvent();
    }
}

class MyService {}
class MyEvent {}