# Java Spring-Boot Https


## Application

Spring `web` application on port 8443

~~~xml
<dependencies> 
     <dependency> 
         <groupId>org.springframework.boot</groupId> 
         <artifactId>spring-boot-starter-web</artifactId> 
     </dependency> 
     <dependency> 
         <groupId>org.springframework.boot</groupId> 
         <artifactId>spring-boot-starter-test</artifactId> 
         <scope>test</scope> 
     </dependency> 
</dependencies> 
~~~

### Main

Map `entries` to Rest Controller.

https://github.com/minte9/spring-boot-pages/blob/4c7099d90a89fd6abbc6fa2bb815f86ea7bb7f49/https/src/main/java/com/minte9/https/App.java#L19-L29

### Port

Set port as `8443` in application.properties

~~~
// src/resources/application.properties
server.port:8443

http://127.0.0.1:8443/hello
    // Hello World
~~~

### Certificate

Create self-signed certificate, using java `keytool` in a new certs/ in root directory.

~~~
cd src/resources/certs/

keytool -genkey -alias tomcat -storetype PKCS12 \
    -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
~~~

### Configure

Update `application.properties` configuration values.

https://github.com/minte9/spring-boot-pages/blob/1503629e9f8c04d17601ec0f9b7766fe54c4f6dc/https/src/main/resources/application.properties#L1-L5

### Run

Check that `https` is enabled on localhost.

~~~
mvn spring-boot:run

https://127.0.0.1:8443/hello
    // Hello World
~~~

### PKCS12

If you already have `.crt and .key` files generated, you need to convert them.

~~~
openssl pkcs12 -export -in certs/myserver.crt -inkey certs/myserver.key \
    -out certs/myserver.p12 -name myserver
~~~


### References

https://tutorialspoint.com/spring_boot/spring_boot_enabling_https.htm  
https://minte9.com/java/spring-boot-https-1452  