## Event-driven CONSUMER - PRODUCT MICROSERVICE (RabbitMQ)


### Add Spring Cloud Stream

Add dependency in build.gradle:

    implementation 'org.springframework.cloud:spring-cloud-starter-stream-rabbit'

    dependencyManagement {
        imports {
            mavenBom "org.springframework.cloud:spring-cloud-dependencies:2024.0.0"
        }
    }

Rebuild:

    cd /event-driven/product-service/
    ./gradlew clean build
    docker compose build --no-cache

### Add Rabbit Config

In application.yml:   

    spring:
        application:
            name: product-review
            
        cloud:
            function:
                definition: product
            
            stream:
                defaultBinder: rabbit
                bindings:
                    product-in-0:
                        destination: products
                        group: product-review
                rabbit:
                    bindings:
                        product-in-0:
                            consumer:
                                autoBindDlq: true

Notice product-in-0, this must match the function name we'll define.   
Also, without a group, Spring may not create a durable queue.  


### Create Event Class

Event contracts must match producer structure:  

    package com.example.review.event;

    public record ProductCreateEvent(
        int productId,
        String name,
        int weight
    ) {}

### Create Consumer Function

Function name: product  
Input binding: product-in-0  

This maps the destination products.  

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

### Test it

Rebuild:

    docker compose down
    docker compose build --no-cache
    docker compose up -d

    OR

    docker compose down -v
    docker compose up --build

After rebuild you should see:

    FunctionCatalog
    Binding 'product-in-0' to destination 'products'

And in RabbitMQ UI:

    - Exchange: products
    - Queues: 1

When you: 

    curl -X POST localhost:7001/product \
        -H "Content-Type: application/json" \
        -d '{"productId":22,"name":"Test","weight":22}'

You should see in review-service logs:

    docker compose logs -f review-service

    review-service  | Received product event: ProductCreateEvent[productId=22, name=Test, weight=22]

It works!


### Milestone

We have now successfully implemented:

    - Reactive REST
    - Reactive Mongo
    - Reactive Messaging
    - RabbitMQ broker
    - Event publishing
    - Event consumption
    - Functional binding model
    - Consumer groups