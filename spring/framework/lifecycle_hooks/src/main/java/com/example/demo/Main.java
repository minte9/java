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