### Authentication Mechanisms

With security starter we automatically get basic authentication.

Project struture:

~~~sh
pom.xml
src/main/java
└── com/example
    ├── SpringSecurityInmemoryApplication.java
    ├── config
    │   └── SecurityConfig.java
    └── controller
        └── RestController.java
~~~

### 1. Dependencies Starters

Sprint Web: embedded Tomcat, Spring MVC, REST controllers.  
Spring Security: authentication, authorization, security filter chain.  

~~~xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
            http://maven.apache.org/POM/4.0.0
            https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>spring-security-in-memory</artifactId>
    <version>1.0.0</version>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
~~~

### 2. Main class

Main Spring Boot Application.

~~~java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityInmemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityInmemoryApplication.class, args);
    }
}
~~~

### 3. Security Configur

Defines public/private urls, login method, in-memory users, password encoder.

~~~java
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) 
                throws Exception {
        
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public").permitAll()
                .anyRequest().authenticated()            
            )
            .httpBasic(Customizer.withDefaults())

        return http.build();
    }
}
~~~

### 4. Controller

~~~java
package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public access";
    }

    /**
     * Private endpoint.
     * 
     * Require username/password.
     */
    @GetMapping("/private")
    public String privateEndpoint() {
        return "Private access";
    }
}
~~~

### 5. Run and Test

~~~sh
mvn spring-boot:run

curl http://localhost:9090/public
curl http://localhost:9090/private

curl -u myuser:mypass http://localhost:9090/private
~~~

