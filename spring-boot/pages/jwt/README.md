# Java Spring-Boot Jwt

## App

Web Spring application with `token auth`, jjwt dependency.

~~~xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt</artifactId>
		<version>0.9.1</version>
	</dependency>
	<dependency>
		<groupId>jakarta.xml.bind</groupId>
		<artifactId>jakarta.xml.bind-api</artifactId>
	</dependency>
<dependencies>
~~~
https://github.com/minte9/spring-boot-pages/blob/8efa4048e9f52eb597d96ffe402e2fe18b504d13/jwt/src/main/java/com/minte9/jwt/App.java#L6-L12

### Controller

REST controller that implements the auth with `username/password`.

https://github.com/minte9/spring-boot-pages/blob/8efa4048e9f52eb597d96ffe402e2fe18b504d13/jwt/src/main/java/com/minte9/jwt/AppRestController.java#L30-L78

### Config

Calls to `/user` are allowed, but all other calls require authentication.

https://github.com/minte9/spring-boot-pages/blob/8efa4048e9f52eb597d96ffe402e2fe18b504d13/jwt/src/main/java/com/minte9/jwt/AppSecurityConfig.java#L20-L35

### Authorization

Implement the autorization process with `JWTAuthorizationFilter` class.

https://github.com/minte9/spring-boot-pages/blob/8efa4048e9f52eb597d96ffe402e2fe18b504d13/jwt/src/main/java/com/minte9/jwt/JWTAuthorizationFilter.java#L31-L121

### Token

The client uses token to access the `protected` resources.

https://github.com/minte9/spring-boot-pages/blob/0b197caff078c6e303a11481a7cb239addc72c03/jwt/hello.sh#L1-L11

### References

https://www.educative.io/answers/session-based-authentication-vs-token-based-authentication  
https://blog.softtek.com/en/token-based-api-authentication-with-spring-and-jwt  
https://github.com/sebascastillo89/jwtDemo/tree/master/src/main/java/es/softtek/jwtDemo  
https://minte9.com/ubuntu/tools-webservices-1453  
https://minte9.com/java/spring-boot-jwt-1454  
