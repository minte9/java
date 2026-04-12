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
