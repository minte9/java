## IoC (Inversion of Control)

### What IoC means

The control of creating and wiring objects is moved out of your code and  
into a container/framework.

In plain java, we often do this ourselves with `new`.  

In Spring, the IoC `container` creates objects and connects their dependencies.  
In Spring, that container is commonly represented by `ApplicationContext`.  

When a framework manages object creation, your classes can `focus` on business logic,  
not on:

- deciding which implementation to create
- creating dependency chains manually
- wiring objects together everywhere

Spring's container is responsible for instantianting, configuring, and assembling `beans`  
based on configuration metadata such as configuration classes and `@Bean` methods. 


### Manual object creation

Without IoC: manual object creation.

~~~java
/**
 * Plain Java without Spring
 * 
 * We crete dependencies manually with new 
 */

package com.example.manual_creation;

public class Main {
    public static void main(String[] args) {
        MessageService service = new MessageService();
        NotificationController controller = new NotificationController(service);

        controller.print();  // Hello from MessageService
    }
}

class MessageService {
    public String getMessage() {
        return "Hello from MessageService";
    }
}

class NotificationController {
    private final MessageService messageService;

    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
~~~

This is simple for small programs, but in larger apps it becomes hard to manage.  


### With Factory pattern

~~~java
/**
 * Factory pattern
 * 
 * Creation is centralized, but still controlled by our code 
 */

package com.example.factory_pattern;

public class Main {
    public static void main(String[] args) {
        NotificationController controller = AppFactory.createNotificationController();

        controller.print();  // Hello from MessageService
    }
}

class AppFactory {
    public static NotificationController createNotificationController() {
        MessageService service = new MessageService();
        
        return new NotificationController(service);
    }
}

class NotificationController {
    private final MessageService messageService;

    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }

}

class MessageService {
    public String getMessage() {
        return "Hello from MessageService";
    }
}
~~~

What improved: 
- The creation logic is centralized

What did not change: 
- The code still controlls object creation


### With Spring IoC

~~~xml
<!-- ========================================
     Maven build file for plain Spring Framework
     No Spring Boot
     Java 17
     ======================================== -->
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example.ioc</groupId>
    <artifactId>demo</artifactId>
    <version>1.0</version>
    <name>ioc</name>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring.version>6.2.17</spring.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
</project>
~~~

~~~java
package com.example.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                 new AnnotationConfigApplicationContext(AppConfig.class);

        NotificationController controller = 
            context.getBean(NotificationController.class);

        controller.print();  // Hello from MessageService
    }
}

// Configuration

@Configuration
class AppConfig {

    @Bean
    MessageService messageService() {
        return new MessageService();
    }

    @Bean
    NotificationController notificationController(MessageService messageService) {
        return new NotificationController(messageService);
    }
}

// Beans

class MessageService {
    public String getMessage() {
        return "Hello from MessageService";
    }
}

class NotificationController {
    private final MessageService messageService;

    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
~~~

Now Spring does the important wiring work:

- creates MessageService
- creates NotificationController
- injects MessageService into NotificationController

That is IoC: control moved from you app code to the Spring container.  

Spring supports Java-based configuration through `@Configuration` and `@Bean`,  
and `ApplicationContext` is the central IoC container interface.  