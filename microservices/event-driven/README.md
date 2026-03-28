## EVENT-DRIVEN (RabbitMQ)

We are moving from REST-based composition to:  

    Event-Driven Microservices

Right now we have:

    product-composite-service
        ↓ REST
    product-service
    review-service
    recommendaton-service

    - Synchrounous
    - Tight coupling
    - Runtime dependency between services
    - Not cloud-native resilient

We move to Event-driven arhitecture using messaging:

    - Loose coupling
    - Asynchrounous communication
    - Better scalability
    - Resilience
    - True reactive microservices

### Conceptual shift

Instead of:
    
    "Composite calls product-service via REST"

We move to:

    "Composite publishes a ProductCreated event"
    "Other services react to the event"

## RabbitMQ

Add RabbitMQ to docker-compose.yml

    rabbitmq:
        image: rabbitmq:3.12-management
        ports:
            - "5672:5672"
            - "15672:15672"
        environment:
            RABBITMQ_DEFAULT_USER: user
            RABBITMQ_DEFAULT_PASS: password

    docker compose down
    docker container prune -f
    docker compose up -d --build

Management UI will be at:

    http://localhost:15672

RabbitMQ vs Kafka:

    - Simpler than Kafka
    - Lightweight
    - Easier Docker setup
    - Enough for learning event-driven microservices

#
#
### Spring Cloud Stream

Instead of manually handling RabbitMQ, you use Spring Cloud Stream.
This is the key, it abstracts messaging.  

Add dependency in build.gradle (all services):

    implementation 'org.springframework.cloud:spring-cloud-starter-stream-rabbit'

We will move toward:

    Create Product
        ↓
    Publish ProductCreateEvent
        ↓
    review-service receives event
    recommendation-service receives event

Instead of:

    - Composite calling other services to create review/recommendations

You:

    - Emit an event
    - Other services react and create their own data

### High-Level Event Flow

When product is created:

    - product-service saves product
    - product-service publishes ProductCreateEvent
    - review-service listens -> creates default reviews
    - recommendation-service listens -> creates default recommendations

This is loose coupling.  

The next step will be:

    - Create an event package
    - Define ProductCreateEvent
    - Publish a simple test event
    - Consume it in one service

#
## Step 1 - Create an Event Mmodel

Create a new package in each service that will use events.

    event/

Inside it, create a shared event class.  

    
    public record ProductCreatedEvent(
        int productId,
        String name,
        int weight
    ) {}

