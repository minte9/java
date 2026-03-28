## Event-driven PRODUCER - PRODUCT MICROSERVICE (RabbitMQ)


### Step 1 - Add Spring Cloud Stream

Instead of manually handling RabbitMQ, you use Spring Cloud Stream.  
This is the key, it abstracts messaging.  

Add dependency in build.gradle (all services):

    implementation 'org.springframework.cloud:spring-cloud-starter-stream-rabbit'


### Import Spring Cloud BOM

Spring Cloud versions must match Spring Boot version.  
Instead of manually versioning each dependency, you import a BOM:  

Add this in build.gradle:

    dependencyManagement {
        imports {
            mavenBom "org.springframework.cloud:spring-cloud-dependencies:2024.0.0"
        }
    }

Then your dependency has NO version.

    implementation 'org.springframework.cloud:spring-cloud-starter-stream-rabbit'

Rebuild:

    cd /event-driven/product-service/
    ./gradlew clean build
    docker compose build --no-cache


### Step 2 - Create an Event Mmodel

Create a new package in each service that will use events.

    event/

Inside it, create a shared event class.  

    public record ProductCreatedEvent(
        int productId,
        String name,
        int weight
    ) {}


### Step 3 - Configure appliction.yml

Add:

    spring:
        cloud:
            stream:
                defaultBinder: rabbit
                bindings:
                    product-out-0:
                        destinations: products
                    rabbit:
                        bindings:
                            product-out-0:
                                producer:
                                    autoBindDlq: true

This creates a Rabbit exchange names:

    products


### Step 4 - Create Event Publisher

In event/

    import org.springframework.context.annotation.Bean;
    import java.util.function.Supplier;
    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Sinks;

    public class EventPublisher {
        
        private final Sinks.Many<ProductCreateEvent> sink = 
            Sinks.many().multicast().onBackpressureBuffer();
            
        @Bean
        public Supplier<Flux<ProductCreateEvent>> productOut() {
            return sink::asFlux;
        }

        public void publish(ProductCreateEvent event) {
            sink.tryEmitNext(event);
        }
    }


### Step 5 - Publish when Product is Created

Inject publisher and modify create in ProductServiceImplementation.  

You are evolving from:

    save -> map ->return    TO:
    save -> map -> publish event -> return

Create a Mapper in persistence/

    import org.springframework.stereotype.Component;
    import com.example.product.api.Product;

    @Component
    public class ProductMapper {
        
        public ProductEntity apiToEntity(Product product) {
            return new ProductEntity(
                null, // let Mongo generate _id
                product.productId(),
                product.name(),
                product.weight()
            );
        }

        public Product entityToApi(ProductEntity entity) {
            return new Product(
                entity.productId(),
                entity.name(),
                entity.weight()
            );
        }
    }

Inject publisher and modify create in ProductServiceImpl.

    @Override
    public Mono<Product> create(Product product) {

        return repository.save(mapper.apiToEntity(product))
                .map(mapper::entityToApi)
                .doOnNext(saved -> 
                    publisher.publish(
                        new ProductCreateEvent(
                            saved.productId(),
                            saved.name(),
                            saved.weight()
                        )
                    )
                );
    }

Now, when product is created:

    Event is emitted -> Spring Cloud Stream sends to RabbitMQ.


### Step 6 - Test it

Rebuild:

    docker compose down
    ./gradlew clean build -x test
    docker compose build --no-cache
    docker compose up -d

    http://localhost:15672/

    docker compose logs -f product-service

Create a product:

    curl -X POST localhost:7001/product \
        -H "Content-Type: application/json" \
        -d '{"productId":30,"name":"Test","weight":3000}'

Then check RabbitMQ UI.  

You should see:  

    Exchange: productOut-out-0

Messages flowing, even if no consumer yet.  

Only after confirming publishing works:

    Add consumer in review-service

See review-service implementation (consumer)