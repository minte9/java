### Context (ApplicationContext)

Spring Context = the container that manages all beans.
Spring decides:

~~~sh
- when to crete objects
- how to connect them
- how long they live
~~~
~~~java
// The main interface:
org.springframework.context.ApplicationContext
~~~
~~~java
// Instead of doing:
new A(new B());

// You do:
context.getBean(A.class);
~~~

### Basic Example

~~~java
package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Main {
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        MyService s = context.getBean(MyService.class);
        s.run();  // Running ...

        context.close();
    }
}

@Configuration
@ComponentScan
class AppConfig {

}

@Component
class MyService {
    public void run() {
        System.out.println("Running ...");
    }
}
~~~

### Getting Beans

~~~java
// =================
// Ways of get beans
// =================

// by type
MyService s1 = context.getBean(MyService.class);

// by name
MyService s2 = (MyService) context.getBean("myService");

// by name + type
MyService s3 = context.getBean("myService", MyService.class);
~~~

### Closing the Context

~~~java
// ==================
// Shutdown container
// ==================

AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

context.close(); // triggers @PreDestroy

// Important for: releasing resources & close connections
~~~