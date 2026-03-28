package com.example.product.event;

import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class EventPublisher {
    
    private final Sinks.Many<ProductCreateEvent> sink = 
        Sinks.many().multicast().onBackpressureBuffer();
        
    @Bean
    public Supplier<Flux<ProductCreateEvent>> product() {
        return sink::asFlux;
    }

    public void publish(ProductCreateEvent event) {
        sink.tryEmitNext(event);
    }
}
