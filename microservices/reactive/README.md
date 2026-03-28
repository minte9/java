## REACTIVE MICROSERVICES (Project Reactor)

In Spring Boot 3, reactive =

    - Spring WebFlux (instead of MVC)
    - Non-blocking HTTP layer
    - Reactive MongoDB driver
    - Return types: Mono<T> and Flux<T>

The reactive support in Spring is based on `Project Reactor`.  

Spring WebFlux, Spring WebClient and Spring Data rely on it  
to provide their reactive and non-blocking feature.  

The core data types in Project Reactor are `Flux` and `Mono`.

### What Changes Under the Hood?

    Before: 

        Tomcat  
        Blocking  
        MongoRepository  
        List  
        Object  

    After:  

        Netty  
        Non-blocking  
        ReactiveMongoRepository  
        Flux  
        Mono  


Implementation on recommendation-service.