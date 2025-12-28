# Java Spring-Boot Scheduled Task

## Awaitility

Initialize a spring new project and add `awaitility` library as dependency in pom.xml

~~~xml
<dependency>
  <groupId>org.awaitility</groupId>
  <artifactId>awaitility</artifactId>
  <version>3.1.2</version>
  <scope>test</scope>
</dependency>
~~~

### Enable

Enable scheduling in you App with `@EnableScheduling` annotation.

https://github.com/minte9/spring-boot-pages/blob/02bd62b3fbab6baf1e0edcf7077a8bb0571bbaa7/scheduled_task/src/main/java/com/minte9/scheduled_task/App.java#L6-L14

### Task

Create you `component` class to manage scheduled tasks.

https://github.com/minte9/spring-boot-pages/blob/02bd62b3fbab6baf1e0edcf7077a8bb0571bbaa7/scheduled_task/src/main/java/com/minte9/scheduled_task/Task.java#L7-L19

### Run

Compile and `run` you application.

~~~sh
mvn spring-boot:run

# Time is: 17:36:24
# Time is: 17:36:25
# Time is: 17:36:26
~~~

### References

https://spring.io/guides/gs/scheduling-tasks  
https://minte9.com/java/spring-boot-scheduled-task-1308  