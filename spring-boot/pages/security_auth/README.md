# Java Spring-Boot Security Auth

## Application

With `security` we automatically get basic authentication.

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
~~~

### Main 

Spring web project with starter security and `thymeleaf` dependencies.

https://github.com/minte9/spring-boot-pages/blob/91ed17f8702632a9933d37adf1dccae77b8f4237/security_auth/src/main/java/com/minte9/security_auth/App.java#L10-L21

### Configure

Configure Spring MVC and set `view controllers` and templates.

https://github.com/minte9/spring-boot-pages/blob/e2bcdedbe896c2d9be17ec51a0e85fa5dbb58e96/security_auth/src/main/java/com/minte9/security_auth/MvcConfig.java#L10-L17

### Autorize

Configure `security` config, allowing access to index

https://github.com/minte9/spring-boot-pages/blob/e47c47b9efb5b3b22ba7376245db66aef18664cb/security_auth/src/main/java/com/minte9/security_auth/SecurityConfig.java#L17-L32

### Store Password

Avoid storing raw password, `bcrypt` it with and Spring CLI.

~~~sh
spring encodepassword mypass

# {bcrypt}$2a$10$2wRXv3x28CiFAq966H93PeAvaRHKMF.ItkMC.CsPBdYTZ2xLO2sLy
~~~
~~~ini
# src/resources/application.properties

spring.security.user.name=myuser
spring.security.user.password={bcrypt}$2a$10$2wRXv3x28CiFAq966H93...

server.servlet.context-path=/myapp
~~~

### Templates

Add index and `user login` form template.

https://github.com/minte9/spring-boot-pages/blob/91ed17f8702632a9933d37adf1dccae77b8f4237/security_auth/src/main/resources/templates/index.html#L1-L4
https://github.com/minte9/spring-boot-pages/blob/91ed17f8702632a9933d37adf1dccae77b8f4237/security_auth/src/main/resources/templates/user.html#L1-L4

### Tests

Test `login` by accesing app entry points.

~~~sh
mvn spring-boot:run

http://localhost:8080/myapp
http://localhost:8080/myapp/user

# Welcome
# Please sign in 
~~~

### References

https://spring.io/guides/gs/securing-web  
https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle#boot-features-security  
https://stackoverflow.com/questions/37285016/what-is-username-and-password-when-starting-spring-boot-with-tomcat  
https://stackoverflow.com/questions/26905721/decode-the-bcrypt-encoded-password-in-spring-security-to-deactivate-user-account  
https://howtoprogram.xyz/2016/08/28/install-spring-boot-command-line-interface-on-linux  
https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing.cli  
https://docs.spring.io/spring-boot/docs/current/reference/html/cli.html  
https://minte9.com/java/spring-boot-security-auth-1306  