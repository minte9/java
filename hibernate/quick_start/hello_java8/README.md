## Hibernate "Hello World"

We'll use:

    - Java 8
    - Hibernate 5.6.x
    - H2 in-memory database
    - hibernate.cfg.xml configuration

    hello-java8/
    │
    ├── pom.xml
    │
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/
        │   │       └── example/
        │   │           ├── Main.java
        │   │           └── Message.java
        │   │
        │   └── resources/
        │       └── hibernate.cfg.xml
        │
        └── test/
            └── java/

### Dependencies

pom.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="
            http://maven.apache.org/POM/4.0.0
            https://maven.apache.org/xsd/maven-4.0.0.xsd">

        <modelVersion>4.0.0</modelVersion>

        <groupId>demo</groupId>
        <artifactId>hello-world</artifactId>
        <version>1.0</version>

        <properties>
            <maven.compiler.source>8</maven.compiler.source>
            <maven.compiler.target>8</maven.compiler.target>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        </properties>

        <dependencies>

            <!-- Hibernate 5 (Java 8 compatible) -->
            <dependency>
                <groupId>org.hibernate</groupId>
                <artifactId>hibernate-core</artifactId>
                <version>5.6.15.Final</version>
            </dependency>

            <!-- H2 Database -->
            <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <version>2.2.224</version>
                <scope>runtime</scope>
            </dependency>

            <!-- JPA API (javax, not jakarta!) -->
            <dependency>
                <groupId>javax.persistence</groupId>
                <artifactId>javax.persistence-api</artifactId>
                <version>2.2</version>
            </dependency>

            <!-- Logging -->
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-simple</artifactId>
                <version>1.7.36</version>
            </dependency>

        </dependencies>

    <build>
        <plugins>
            <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
            </plugin>

            <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.4</version>
            <executions>
                <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
                <configuration>
                    <createDependencyReducedPom>false</createDependencyReducedPom>
                    <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>com.example.Main</mainClass>
                    </transformer>
                    </transformers>
                </configuration>
                </execution>
            </executions>
            </plugin>
        </plugins>
        </build>

    </project>


### Hibernate Configuration

src/main/resources/hibernate.cfg.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE hibernate-configuration PUBLIC
            "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
            "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

    <hibernate-configuration>
        <session-factory>

            <!-- Database connection -->
            <property name="hibernate.connection.driver_class">org.h2.Driver</property>
            <property name="hibernate.connection.url">jdbc:h2:mem:testdb</property>
            <property name="hibernate.connection.username">sa</property>
            <property name="hibernate.connection.password"></property>

            <!-- SQL dialect -->
            <property name="hibernate.dialect">org.hibernate.dialect.H2Dialect</property>

            <!-- Show SQL -->
            <property name="hibernate.show_sql">true</property>

            <!-- Auto create schema -->
            <property name="hibernate.hbm2ddl.auto">create</property>

            <!-- Entity mapping -->
            <mapping class="com.example.Message"/>

        </session-factory>
    </hibernate-configuration>


## Main Class (Hello World)

    import org.hibernate.Session;
    import org.hibernate.SessionFactory;
    import org.hibernate.cfg.Configuration;

    public class Main {
        public static void main(String[] args) {

            java.util.logging.LogManager.getLogManager().reset();
            
            SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

            Session session = factory.openSession();
            session.beginTransaction();

            Message message = new Message("Hello Hibernate (Java 8)!");
            session.save(message);

            session.getTransaction().commit();

            System.out.println("Saved message with ID: " + message.getId());

            session.close();
            factory.close();
        }    
    }

### Entity Class (Message)

    import javax.persistence.*;

    @Entity
    @Table(name = "messages")
    public class Message {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String text;

        public Message() {}

        public Message(String text) {
            this.text = text;
        }

        public Long getId() {
            return id;
        }
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


### Run

    mvn clean build
    java -jar target/hello-world-1.0.jar

    Saved message with ID: 1

#
## How H2 works

H2 has two main modes:

A) In-Memory Mode:

    <property name="hibernate.connection.url">jdbc:h2:mem:testdb</property>

    - Database lives only inside JVM memory
    - When you app stops, database is gone
    - That's why you ID is always 1

B) File-Based Mode (real persistence)

    <property name="hibernate.connection.url">jdbc:h2:./testdb</property>

    - Data is stored on disk
    - You can stop the app
    - Run it again
    - Data is still there

#
## Full Example (Save + Retrive)

Change hibernate.cfg.xml:

    <property name="hibernate.connection.url">jdbc:h2:./testdb</property>

### Retrive Message By ID

Using session.get()

    Message message = session.get(Message.class, 1L);

Hibernate generates:

    select * from messages where id = 1


### Main Class

Changes in the main class:

     // First Run
    try (Session session = factory.openSession()) {
        session.beginTransaction();
        Message message = new Message("Hello Database!");
        session.save(message);
        session.getTransaction().commit();

        System.out.println("Saved ID: " + message.getId());
        session.close();
    }

    // Second Run
    try (Session session = factory.openSession()) {
        
        Message item = session.get(Message.class, 1L);

        if (item != null) {
            System.out.println("Found: " + item.getText());
        } else {
            System.out.println("Not found");
        }
        session.close();
    }

### Important

You are using 'create' in schema settings.   
Change it to 'update', otherwise Hibernate drops and recreate tables every time.  

    <property name="hibernate.hbm2ddl.auto">update</property>
    <property name="hibernate.show_sql">false</property>


## Run Again

    mvn clean build
    java -jar target/hello-world-1.0.jar 
        Saved ID: 2
        Found: Hello Database!

    java -jar target/hello-world-1.0.jar 
        Saved ID: 3
        Found: Hello Database!