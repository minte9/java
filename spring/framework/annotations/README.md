### Annotations

Annotation mechanism (@Something) is a Java language feature.    
The @Configuration, @Bean are Spring Framework features.  

Annotations do nothing by themselves unless something process them.  
Spring reads these annotations and changes behavior at runtime.  

~~~java
@Configuration
class AppConfig {

    @Bean
    MessageService messageService() {
        return new MessageService();
    }
}
~~~

What Spring interprets:

- scan AppConfig
- find @Bean methods
- call messageService()
- register result as a "bean"

@Configuration → marks class as source of bean  definitions
@Bean → tell Springs: "this method creates an object you should manage"


### IoC using annotations only

Instead of writing "recipes" (@Bean methods), we mark classes and let Spring:

- detect them
- create them
- inject dependencies

~~~java
import org.springframework.stereotype.Component;

@Component
class MessageService {
    public String getMessage() {
        return "Hello from MessageService";
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
</dependencies>
~~~
~~~java
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
~~~

We did NOT write:

~~~java
new MessageService()
new NotificationController(...)

@Bean
~~~

Spring did everything based on annotations + classpath scanning. 