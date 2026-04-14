package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

public class Main {
    public static void main(String[] args) {
        
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        BankService service = context.getBean(BankService.class);
        service.transferMoney();

        // >> Security check
        // Transferring money...
        // >> Logging after method
    }
}

@Configuration
@ComponentScan("com.example")
@EnableAspectJAutoProxy  // enable AOP - Look Here
class AppConfig {

}

@Component
class BankService {

    public void transferMoney() {
        System.out.println("Transferring money...");
    }
}

// ======================
// Aspect (this is a AOP)
// ======================

@Aspect
@Component
class LoggingAspect {

    @Before("execution(* BankService.transferMoney(..))")
    public void before() {
        System.out.println(">> Security check");
    }

    @After("execution(* BankService.transferMoney(..))")
    public void after() {
        System.out.println(">> Logging after method");
    }
}
