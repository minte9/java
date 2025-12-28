# Java Spring-Boot Command Line

## CLI

Spring Boot CLI is a `command line` tool used to quickly develop a Spring application.

~~~sh
sudo mkdir /opt/spring-boot
sudo tar xzf spring-boot-cli-2.6.3-bin.tar.gz -C /opt/spring-boot/

sudo gedit /etc/profile
    # export SPRING_HOME=/opt/spring-boot/spring-2.6.3
    # export PATH=$SPRING_HOME/bin:$PATH
source /etc/profile

spring --version # Spring CLI v2.6.3
sprint init --list
~~~

### Run

Compile and run `Groovy` source code with spring.

https://github.com/minte9/spring-boot-pages/blob/4fcf610c508357e7fc2d3a9f81389b37bb79f29c/command_line/src/main/java/com/minte9/command_line/App.groovy#L1-L13
~~~sh
spring run src/main/java/com/minte9/command_line/App.groovy

http://localhost:8080
    # Hello Groovy
~~~

### Jar

Use `jar` command to package the application.

~~~sh
cd src/main/java/com/minte9/command_line/
spring jar hello.jar *.groovy

cd target/classes/com/minte9/command_line/
java -jar hello.jar
~~~

### References

https://repo.spring.io/ui/native/release/org/springframework/boot/spring-boot-cli  
https://howtoprogram.xyz/2016/08/28/install-spring-boot-command-line-interface-on-linux  
https://docs.spring.io/spring-boot/docs/current/reference/html/cli.html  
https://github.com/spring-projects/spring-boot/tree/v2.5.4/spring-boot-project/spring-boot-cli/samples  
https://minte9.com/java/spring-boot-command-line-1307  