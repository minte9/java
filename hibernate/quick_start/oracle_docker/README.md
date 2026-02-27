## Hibernate with Oracle

### Docker

We will use docker-compose to start Oracle and Spring Boot.

    - Oracle / 1522 port
    - Spring Boot / 8085 port
    - Lightweight Oracle XE image: gvenzl/oracle-xe

### Project Structure

    oracle-docker/
    │
    ├── docker-compose.yml
    ├── Dockerfile
    ├── pom.xml
    └── src

### POM

    <project xmlns="http://maven.apache.org/POM/4.0.0"
		     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
		     http://maven.apache.org/xsd/maven-4.0.0.xsd">

		<modelVersion>4.0.0</modelVersion>

		<groupId>com.example</groupId>
		<artifactId>hello-oracle</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<name>hello-oracle</name>
		<description>Spring Boot + Hibernate + Oracle Docker Example</description>
		<packaging>jar</packaging>

		<!-- Spring Boot Parent -->
		<parent>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-parent</artifactId>
		    <version>3.2.5</version>
		    <relativePath/>
		</parent>

		<properties>
		    <java.version>17</java.version>
		</properties>

		<dependencies>

		    <!-- Spring Web -->
		    <dependency>
		        <groupId>org.springframework.boot</groupId>
		        <artifactId>spring-boot-starter-web</artifactId>
		    </dependency>

		    <!-- Spring Data JPA (includes Hibernate) -->
		    <dependency>
		        <groupId>org.springframework.boot</groupId>
		        <artifactId>spring-boot-starter-data-jpa</artifactId>
		    </dependency>

		    <!-- Oracle JDBC Driver -->
		    <dependency>
		        <groupId>com.oracle.database.jdbc</groupId>
		        <artifactId>ojdbc11</artifactId>
		        <scope>runtime</scope>
		    </dependency>

		    <!-- Testing -->
		    <dependency>
		        <groupId>org.springframework.boot</groupId>
		        <artifactId>spring-boot-starter-test</artifactId>
		        <scope>test</scope>
		    </dependency>

		</dependencies>

		<build>
		    <plugins>

		        <!-- Spring Boot Maven Plugin -->
		        <plugin>
		            <groupId>org.springframework.boot</groupId>
		            <artifactId>spring-boot-maven-plugin</artifactId>
		            <configuration>
		                <layers>
		                    <enabled>true</enabled>
		                </layers>
		            </configuration>
		        </plugin>

		    </plugins>
		</build>

	</project>


### Docker Compose

	version: '3.8'

	services:

	  oracle-db:
		image: gvenzl/oracle-xe:21-slim
		container_name: oracle-xe
		ports:
		  - "1522:1521"
		environment:
		  ORACLE_PASSWORD: oracle
		  APP_USER: appuser
		  APP_USER_PASSWORD: apppass
		volumes:
		  - oracle-data:/opt/oracle/oradata
		networks:
		  - app-network

	  spring-app:
		build: .
		container_name: spring-oracle-app
		ports:
		  - "8085:8080"
		depends_on:
		  - oracle-db
		environment:
		  SPRING_DATASOURCE_URL: jdbc:oracle:thin:@oracle-db:1521/XEPDB1
		  SPRING_DATASOURCE_USERNAME: appuser
		  SPRING_DATASOURCE_PASSWORD: apppass
		networks:
		  - app-network

	volumes:
	  oracle-data:

	networks:
	  app-network:

### Important

In Docker: 

    localhost ≠ your machine

Spring connects to:

    oracle-db

That is the service name, not localhost.


### Dockerfile (Spring Boot)

We use multi-stage build for clean image.

    # ---------- Build stage ----------
    FROM maven:3.9.6-eclipse-temurin-17 AS build

    WORKDIR /app
    COPY pom.xml .
    COPY src ./src

    RUN mvn clean package -DskipTests

    # ---------- Runtime stage ----------
    FROM eclipse-temurin:17-jdk

    WORKDIR /app
    COPY --from=build /app/target/*.jar app.jar

    EXPOSE 8080

    ENTRYPOINT ["java", "-jar", "app.jar"]


### application.properties

    spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

Spring will read from docker-compose:

    SPRING_DATASOURCE_URL
    SPRING_DATASOURCE_USERNAME
    SPRING_DATASOURCE_PASSWORD


### Entity Model

Message.java

	import jakarta.persistence.*;

	@Entity
	@Table(name = "messages")
	public class Message {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String text;

		public Message() {}

		public Message(String text) {
			this.text = text;
		}

		public Long getId() { return id; }
		public String getText() { return text; }
		public void setText(String text) { this.text = text; }
	}

### Repository

MessageRepository.java

	import org.springframework.data.jpa.repository.JpaRepository;

	public interface MessageRepository extends JpaRepository<Message, Long> {}

### Controller

	import org.springframework.web.bind.annotation.*;

	@RestController
	@RequestMapping("/api")
	public class MessageController {

		private final MessageRepository repository;

		public MessageController(MessageRepository repository) {
			this.repository = repository;
		}

		@GetMapping("/hello")
		public String hello() {
			Message message = new Message("Hello World from Oracle!");
			repository.save(message);

			return repository.findById(message.getId())
					.map(m -> "ID: " + m.getId() + ", Text: " + m.getText())
					//.map(Message::getText)
					.orElse("Not found");
		}
	}

### Application

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;

	@SpringBootApplication
	public class MessageApplication {

		public static void main(String[] args) {
			SpringApplication.run(MessageApplication.class, args);
		}
	}


### Build & Run Everything

First startup takes 1-2 minutes (Oracle initialization).  

	docker compose up --build

	curl http://localhost:8085/api/hello
		ID: 1, Text: Hello World from Oracle

	curl http://localhost:8085/api/hello
		ID: 2, Text: Hello World from Oracle

### Errors

1) Connection refused.

Oracle can take a bit to initialize on first run.  
Spring tries to connect and fails.  

In docker-compose.yml:

	spring-app:
  		restart: on-failure