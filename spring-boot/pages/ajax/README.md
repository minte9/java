# Java Spring-Boot Ajax

## Ajax App

Create a new `Spring Web` project.

~~~xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
~~~
https://github.com/minte9/spring-boot-pages/blob/62298f6091c88094c2b418c3aa3d191167225d04/ajax/src/main/java/com/minte9/ajax/App.java#L10-L17

### Client

The `index.html` loads into the user's browser the client that consume REST service.

https://github.com/minte9/spring-boot-pages/blob/c64d1e4521a787e372b0c61fabc43f67380347fc/ajax/public/index.html#L1-L43

### Service

The `REST controller` displays the response as JSON.

https://github.com/minte9/spring-boot-pages/blob/c64d1e4521a787e372b0c61fabc43f67380347fc/ajax/src/main/java/com/minte9/ajax/CronoController.java#L9-L49

### Run

Test and `run` the application.

~~~
mvn spring-boot:run

http://localhost:8080/

// Get current time (with Ajax): Start | Stop
// 13:21:55
~~~

### References

https://spring.io/guides/gs/consuming-rest-jquery  
https://spring.io/guides/gs/rest-service-cors  
https://minte9.com/java/spring-boot-ajax-1310
