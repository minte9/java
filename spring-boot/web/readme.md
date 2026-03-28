## Spring Boot Web


### 📌 Core idea
- Spring Boot Web = build web apps (MVC and REST) with embeded server
- No external Tomcat setup required
- Start app with a singl `main()` method


### 📌 Must-know annotations
- `@SpringBootApplicaton`
    - Marks main class
    - Enables auto-configuration + component scanning

- `@Controller`
    - Used for HTML templates (MVC)

- `@RestController`
    - Used for APIs
    - Returns data, not views

- `@ResponseBody`
    - Forces raw response even in @Controller


### 📌 Templates (Themeleaf)
Templates live in: 

    src/main/resources/templates

Data is passed using:  

    model.addAttribute("key", value)

Access data in template:  

    ${key}


### 📌 Configuration (minimal)
Default port:  `8080`  

Override in:  `src/main/resources/application.properties`

   server.port=9090

### 📌 How to run

    mvn spring-boot:run
    OR 
    
    mvn package
    java -jar target/*.jar