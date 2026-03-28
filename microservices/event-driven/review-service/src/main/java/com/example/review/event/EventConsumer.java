package com.example.review.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class EventConsumer {
    
    @Bean
    public Consumer<ProductCreateEvent> product() {
        return event -> {
            System.out.println("Received product event: " + event);
        }; 
    }
}
