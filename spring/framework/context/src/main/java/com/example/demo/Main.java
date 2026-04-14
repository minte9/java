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
