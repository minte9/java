# Java Spring-Boot Rest Service

## Dependencies

Simple RESTful `web service` with Spring on port 9090.

~~~xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
~~~

### Application

The service will handle GET `requests` and return JSON response.

https://github.com/minte9/spring-boot-pages/blob/e2e236445d2642da52beb07f241108e19b844f5d/rest_service/src/main/java/com/minte9/rest_service/App.java#L14-L20

### Resource

In Spring RESTful web services, HTTP requests are handled by a `resource controller`

https://github.com/minte9/spring-boot-pages/blob/734e3a3d71aafb3cb63b4fbc08006484a6f1395c/rest_service/src/main/java/com/minte9/rest_service/QuoteController.java#L17-L39

### Model

To model the quote representation, create a `resource representation` class. 

https://github.com/minte9/spring-boot-pages/blob/322bf9636725bec8f829d9076673c89ac05133fa/rest_service/src/main/java/com/minte9/rest_service/Quote.java#L7-L24

### Port

You might want to set a `different port` from 8080 for you application.

~~~ini
# src/main/resources/application.properties

server.port=9090
~~~

### Run

We can now `build adn run` the application and test the service.

~~~sh
mvn spring-boot:run

http://localhost:9090/quote/1 # {"id":1,"content":"Spring Boot ...
http://localhost:9090/quote/2 # {"id":2,"content":"With Boot you ...

~~~

### References

https://spring.io/guides/gs/rest-service  
https://medium.com/@gautham/running-your-springboot-application-on-a-different-port-other-than-8080-2952729037f5  
https://spring.io/guides/gs/consuming-rest  
https://minte9.com/java/spring-boot-rest-service-1303  
