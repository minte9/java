### Bean Lifecycle Hooks

Bean Hooks = extension points where you can plug custom logic into the bean lifecycle.  

This allows you to: 
- modify beans before/after initialization
- add cross-cutting behavior (logging, proxies, etc)

### Where hooks fit in lifecycle

~~~java
// ========================================
// Full lifecycle with hooks
// ========================================

// 1. Constructor
// 2. Dependency Injection
// 3. BeanPostProcessor BEFORE init   <-- HOOK
// 4. @PostConstruct / init()
// 5. BeanPostProcessor AFTER init    <-- HOOK
// 6. Bean ready to use
// 7. @PreDestroy / destroy()
~~~

### BeanPostProcessor

Allows you to intercept every bean:
- before initialization
- after initialization

~~~java
// ========================================
// Custom BeanPostProcessor
// ========================================

@Component
class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before Init: " + beanName);
        return bean;  // must return bean
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After Init: " + beanName);
        return bean;
    }
}
~~~

### Real Use Case

You can modify or wrap beans:

~~~java
// ========================================
// Example: wrapping a bean (concept)
// ========================================

@Override
public Object postProcessAfterInitialization(Object bean, String name) {

    if (bean instanceof MessageService) {
        System.out.println("Wrapping MessageService");
        // could return proxy here (AOP does this)
    }

    return bean;
}
~~~

This is how:

- AOP works
- @Transactional works
- proxy are created

### Full Example

~~~java
package com.example.demo;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

public class Main {
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        MyService s = context.getBean(MyService.class);
        s.run();

        context.close();

        // Before Init: appConfig
        // After Init: appConfig
        // Before Init: myService
        // After Init: myService
        // Running ...
    }
}

@Configuration
@ComponentScan
class AppConfig {}

// ==========================================
// Demo Bean
// ==========================================

@Component
class MyService {

    public MyService() {
        System.out.println("1. Constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. @PostConstruct");
    }

    public void run() {
        System.out.println("3. Running ...");
    }
}

// ========================================
// Custom BeanPostProcessor
// ========================================

@Component
class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before Init: " + beanName);
        return bean;  // must return bean
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After Init: " + beanName);
        return bean;
    }
}
~~~

### Best Practices

~~~java
// ========================================
// Rules
// ========================================

// 1. Use @PostConstruct / @PreDestroy for simple logic
// 2. Use BeanPostProcessor for framework-level logic
// 3. Avoid InitializingBean / DisposableBean
// 4. Keep hooks lightweight (they run for ALL beans)
~~~