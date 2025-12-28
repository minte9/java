# Java Spring-Boot Templates

## Application

Spring web project with `Thymealf` and Spring Boot DevTools. 

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
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
~~~

### Controller

The `@Controller` handle GET requests and parses the templates.

https://github.com/minte9/spring-boot-pages/blob/0b928ccdabf7a1e5960f7d7657aa30046300df89/templates/src/main/java/com/minte9/templates/HelloController.java#L14-L41


### Templates

Thymeleaf parses the `resources/templates/hello.html` template.

https://github.com/minte9/spring-boot-pages/blob/e341223f9f1f64b4f0bca391a9e919794174ef47/templates/src/main/resources/templates/hello.html#L1-L10


### Run

With Spring Boot `DevTools` you don't need to restart the server, after code changes.

~~~sh
mvn spring-boot:run

http://localhost:8080
http://localhost:8080/hello
http://localhost:8080/hello/?username=John

# Welcome!
# Hello, guest!
# Hello, John!
~~~

### References

https://spring.io/guides/gs/serving-web-content  
https://spring.io/guides/gs/batch-processing/#_build_an_executable_jar  
https://minte9.com/java/spring-boot-templates-1304  
