## Bean Lifecycle

A bean = an object managed by the Spring IoC container (ApplicationContext).  
Spring controls: creation, initialization, usage destruction  

~~~java
/**
 * ======================
 * Bean lifecycle Example
 * ======================
 *
 * Overview:
 *  1. Instantiate (create object)
 *  2. Inject dependencies
 *  3. Initialize (custom logic)
 *  4. Bean is ready to use
 *  5. Destroy (cleanup when context closes)
 */

package com.example.bean_lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        DatabaseConnection db = context.getBean(DatabaseConnection.class);
        db.query();

        context.close();  // triggers destroy phase

            // 1. Contructor: Bean created
            // 2. @PostConstruct: Initialization logic
            // 3. Bean is in use
            // 4. @PreDestroy: Cleanup logic
    }
}

@Configuration
class AppConfig {

    @Bean
    DatabaseConnection databaseConnection() {
        return new DatabaseConnection();
    }
}


class DatabaseConnection {

    public DatabaseConnection() {
        System.out.println("1. Contructor: Bean created");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. @PostConstruct: Initialization logic");
    }

    public void query() {
        System.out.println("3. Bean is in use");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("4. @PreDestroy: Cleanup logic");
    }
}
~~~
~~~xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>jakarta.annotation</groupId>
        <artifactId>jakarta.annotation-api</artifactId>
        <version>2.1.1</version>
    </dependency>
</dependencies>
~~~


### Bean Scopes

Scope = how many instances of a bean exist and how long they live.

~~~java
/**
 * ===================================
 * 1. Bean Scope - Singleton (DEFAULT)
 * ===================================
 * 
 * Concept:
 *  - one instance  per Spring container
 *  - created when context starts
 *  - reused everywhere
 * 
 * =========================
 * 2. Bean Scope - Prototype
 * =========================
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
~~~


### Bean Autowiring

Wiring = connecting beans (objects) together via dependencies.

~~~java
// ========================
// Manual wiring with @Bean
// ========================

@Configuration
class AppConfig {
    @Bean
    B b() {
        return new B();
    }

    @Bean
    A a(B b) { // dependency passed manually
        return new A(b);
    }
}
~~~

Spring can scan classes and create beans automatically with @Component.  
Instead of manually passing dependencies, Spring does it automatically.  

~~~java
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
~~~


### Stereotype annotations

All are specialized versions of @Component:

~~~java
// ==================
// Common stereotypes
// ==================

@Component   // generic bean
@Service     // business logic
@Repository  // data access
@Controller  // web layer
~~~

Same behavior, better semantics.