## Spring Boot + RabbitMQ

### Simple minimal example

It shows:

- one REST point
- one producer that sends a message to RabbitMQ
- one consumer that receives it
- Docker Compose to start RabbitMQ and the app

The idea:

- You call HTTP endpoint: POST /api/messages
- Spring Boot sends the message to RabbitMQ
- RabbitMQ stores/routes it
- Spring Boot listener consumes it and prints it

### 1) Components

Spring Boot application is the Java application itself.

    MessageController - receives HTTP requests
    MessageProducer - sends messages to RabbitMQ
    MessageListener - receives messages from RabbitMQ
    RabbitConfig - defines queue/exchange/binding
    RabbitMessage - simple payload object

RabbitMQ is the message broker.

    Queue - where messages wait
    Exchange - receives messages from producers and routes them
    Routing key - helps exchange decide where to send messages
    Binding - connection rule between exchange and queue

Docker Compose starts both RabbitMQ container and Spring Boot app container.  
This way we can run the whole demo with one command.  

### 2) Project structure

    spring-rabbit-demo/
    ├─ src/
    │  ├─ main/
    │  │  ├─ java/com/example/demo/
    │  │  │  ├─ DemoApplication.java
    │  │  │  ├─ config/RabbitConfig.java
    │  │  │  ├─ model/RabbitMessage.java
    │  │  │  ├─ messaging/MessageProducer.java
    │  │  │  ├─ messaging/MessageListener.java
    │  │  │  └─ controller/MessageController.java
    │  │  └─ resources/
    │  │     └─ application.properties
    ├─ Dockerfile
    ├─ docker-compose.yml
    └─ pom.xml

### 3) Pom.xml

~~~xml
<!-- REST API support -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- RabbitMQ / AMQP support -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
~~~

Main dependencies:

- spring-boot-starter-web  / Used to expose HTTP endpoints (REST API)
- spring-boot-starter-amqp / Used for RabbitMQ integration (AMQP = Advanced Message Queuing Protocol)
- spring-boot-starter / Core Spring Boot basics
- spring-boot-maven-plugin / Allows us to package the app as an executable JAR  


 ### 4) Main application class

 This class boots the entire application context.

 ~~~java
/**
 * DemoApplication
 * ---------------
 * Entry point of the Spring Boot application.
 * 
 * What @SpringBootApplication does:
 *  - @Configuration
 *      Mark this class as a source of bean definitions
 * 
 *  - @EnableAutoConfiguration 
 *      Lets Spring Boot configure many things automatically
 *      based on dependencies in pom.xml
 *  
 *  - @ComponentScan
 *      Tells Spring to scan this package and subpackages
 *      for classes annoteted with @Component, @Service,
 *      @Repository, @Controller, @RestController, etc.
 * 
 * This class boots the entire application context.
 */

package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
}
~~~

### 5) RabbitMQ configuration

RabbitConfig.java

This class defines RabbitMQ infrastructure as Spring bean.

~~~java
/**
 * RabbitConfig
 * ------------
 * This class defines RabbitMQ infrastructure as Spring bean.
 * 
 * Core RabbitMQ concepts used here:
 * 
 * 1) Queue
 *    A queue stores messages until they are consumed.
 * 
 * 2) Exchnage
 *    Producers usually send messages to an exchange, not directly to a queue.
 *    The exchange decides where the messages goes.
 * 
 * 3) Routing key
 *    A text value attached to the message by the producer.
 *    The exchange uses it to decide which queue should receive the message.
 * 
 * 4) Binding
 *    A rule connecting an exchange to a queue.
 * 
 * Why this config?
 *  - keeps messaging setup centralized
 *  - Spring can auto-create these objects in RabbitMQ on startup
 * 
 * Notes:
 *  - Queue durable true - means that queue definition survives broker restart
 *  - DirectExchange - routes messages to queue by exact routing key match
 */

package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {
 
    public static final String QUEUE_NAME = "demo.queue";
    public static final String EXCHANGE_NAME = "demo.exchange";
    public static final String ROUTING_KEY = "demo.routing.key";

    @Bean
    public Queue demoQueue() {
        return new Queue(QUEUE_NAME, true);  // durable = true
            
    }

    @Bean
    public DirectExchange demoExchange() {
        return new DirectExchange(EXCHANGE_NAME);  // easiest exchange type to understand 
    }

    @Bean
    public Binding demoBinding(Queue demoQueue, DirectExchange demoExchange) {
        return BindingBuilder
                .bind(demoQueue)
                .to(demoExchange)
                .with(ROUTING_KEY);

                // if a message reaches demo.exchange
                // with routing key 'demo.routing.key'
                // send it to demo.queue
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();

            // Tells Spring AMQP to serialize Java objects as JSON when sending,
            // and deserialize JSON back into Java objcets when receiving.
    }
}
~~~

### 6) Message payload object

model/RabbitMessage.java

A simple POJO (Plain Old Java Object) used as the message payload.

~~~java
/**
 * RabbitMessage
 * -------------
 * This is a simple POJO (Plain Old Java Object) 
 * used as the message payload.
 * 
 * Why have a class instead of sending raw String?
 *  - more realistic
 *  - easier to extend later
 *  - easier to map from JSON request body
 * 
 * String / Jackson can convert JSON <-> Java object automatically 
 * as long as getters/setters and default constructor exist. 
 */

package com.example.model;

public class RabbitMessage {
    
    private String text;

    public RabbitMessage() {
    }

    public RabbitMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
~~~

### 7) Producer

MessageProducer.java

Send messages to RabbitMQ.  
RabbitTemplate is the main helper class provided by Spring AMQP.

~~~java
/**
 * MessageProducer
 * ---------------
 * Send messages to RabbitMQ.
 * 
 * Why @Service?
 *  - Marks this class as a Spring-managed bean
 *  - Indicates business/service layer role
 * 
 * RabbitTemplate:
 *  - Main helper class provided by Spring AMQP for sending messages.
 */
package com.example.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.config.RabbitConfig;
import com.example.model.RabbitMessage;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RabbitMessage message) {
        rabbitTemplate.convertAndSend(
            RabbitConfig.EXCHANGE_NAME,
            RabbitConfig.ROUTING_KEY,
            message
        );

        System.out.println(">>> PRODUCER sent message: " + message.getText());
    }
    
}
~~~

### 8) Consumer / Listener

MessageListener.java

Receive messages from RabbitMQ.

~~~java
package com.example.messaging;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.config.RabbitConfig;
import com.example.model.RabbitMessage;

@Component
public class MessageListener {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(RabbitMessage message) 
                    throws InterruptedException {

        Thread.sleep(5000); // simulate slow processing
        System.out.println("<<< CONSUMER received message: " + message.getText());
    }
}
~~~

### 9) REST controller

MessageController.java

Expose HTTP endpoint so we can trigger RabbitMQ messaging from a browser, Postman, curl.

~~~java
package com.example.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.example.messaging.MessageProducer;
import com.example.model.RabbitMessage;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    private final MessageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@ResponseBody RabbitMessage message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ: " + message.getText());
    }
}
~~~

### 10) Application properties

resource/application.properties

Application name (optional, only cosmetic/logging).  
In Docker Compose, the RabbitMQ container name will be "rabbitmq".  

~~~yml
spring.application.name=spring-rabbit-demo

spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

server.port=9090
~~~

### 11) Dockerfile

~~~yml
# ---------- Build stage ----------
FROM maven:3.9.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build jar, skipping tests for simplicity in demo
RUN mvn clean package -DskipTests

# ---------- Run stage ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 9090

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
~~~

### 12) Docker Compose

~~~yml
services:
  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports:
      - "5672:5672"   # AMQP port for app connection
      - "15672:15672" # RabbitMQ Management UI
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest

  app:
    build: .
    container_name: spring-rabbit-demo
    ports:
      - "9090:9090"
    depends_on:
      - rabbitmq
~~~


### 13) Run everything

Change port to 9090 if needed.

~~~sh
docker ps
docker container prune -f

docker compose down
docker compose build --no-cache
docker compose up

~~~

When everything starts:

- Spring Boot app: http://localhost:9090
- RabbitMQ UI: http://localhost:15672
- RabbitMQ login:
    - username: guest
    - password: guest


### 14) How to test

~~~sh
curl -X POST http://localhost:9090/api/messages \
  -H "Content-Type: application/json" \
  -d "{\"text\":\"Hello RabbitMQ from Spring Boot\"}"
~~~

Expected HTTP response:


~~~sh
Message sent to RabbitMQ: Hello RabbitMQ from Spring Boot
~~~

And in app logs you should see:

~~~sh
>>> PRODUCER sent message: Hello RabbitMQ from Spring Boot
<<< CONSUMER received message: Hello RabbitMQ from Spring Boot
~~~

RabbitMQ’s UI is not beginner-friendly at all the first time.   
Look at Queus and Streams and click demo.queue  

Important counters:

- Ready:
    - Messages waiting in queue
    - NOT yet consumed
- Unacked
    - Messages being processed by consumer
    - Not yet confirmed

In this app (without the slow down trick), these will often be 0  
because the messages are consumed instantly.  