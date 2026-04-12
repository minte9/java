### Dependency Injection (DI)

What DI means:

Provide an object's dependencies from the outside  
instead of creating them inside the class.  

DI is how IoC is implemented in Spring.

~~~java
// BAD (no DI):
class A {
    B b = new B(); // tightly coupled
}

// GOOD (DI):
class A {
    private final B b;

    public A(B b) { // dependency is injected
        this.b = b;
    }
}
~~~


### Types of DI

1) Constructor Injection (RECOMMENDED)

~~~java
class MessageService {
    public String getMessage() {
        return "Hello DI";
    }
}

class NotificationController {

    private final MessageService messageService;

    // dependency is REQUIRED and immutable
    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
~~~

Best practice because:
- dependencies are required
- object is always valid
- easier to test

2) Field Injection (NOT recommended)

~~~java
import org.springframework.beans.factory.annotation.Autowired;

class NotificationController {

    @Autowired
    private MessageService messageService;

    public void print() {
        System.out.println(messageService.getMessage());
    }
}
~~~

Problems:
- hard to test
- hidden dependencies
- not immutable


### IoC using DI

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
package com.example.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService service =
            context.getBean(OrderService.class);

        service.processOrder();
            // Payment executed ...
            // Order processed.
    }
}

@Configuration
@ComponentScan(basePackages = "com.example.di")
class AppConfig {

}

@Service
class PaymentService {
    
    public void pay() {
        System.out.println("Payment executed ...");
    }
}

@Service
class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processOrder() {
        paymentService.pay();
        System.out.println("Order processed.");
    }
}
~~~
