/**
 * Bean lifecycle - Example
 * 
 * Overview:
 *  1. Instantiate (create object)
 *  2. Inject dependencies
 *  3. Initialize (custom logic)
 *  4. Bean is ready to use
 *  5. Destroy (cleanup when context closes)
 */

package com.example.bean_lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        DatabaseConnection db = context.getBean(DatabaseConnection.class);
        db.query();

        context.close();  // triggers destroy phase

            // 1. Contructor: Bean created
            // 2. @PostConstruct: Initialization logic
            // 3. Bean is in use
            // 4. @PreDestroy: Cleanup logic
    }
}

@Configuration
class AppConfig {

    @Bean
    DatabaseConnection databaseConnection() {
        return new DatabaseConnection();
    }
}


class DatabaseConnection {

    public DatabaseConnection() {
        System.out.println("1. Contructor: Bean created");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. @PostConstruct: Initialization logic");
    }

    public void query() {
        System.out.println("3. Bean is in use");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("4. @PreDestroy: Cleanup logic");
    }
}
