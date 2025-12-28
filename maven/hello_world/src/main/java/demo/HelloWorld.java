/**
 * MAVEN BUILD - HELLO WORLD EXAMPLE
 * ---------------------------------
 * This is a simple Java application build using Maven.
 * 
 * PURPOSE:
 * -------
 *  - Demonstrate how Maven manages external dependencies.
 *  - Show that third-party libraries can be used WITHOUT 
 *    manually downloading JAR files.
 * 
 * KEY CONCEPTS:
 * -------------
 *  - pom.xml defines project metadata and dependencies
 *  - Maven download required libraries automatically
 * 
 * KEY MAVEN COMMANDS:
 * -------------------
 * mvn compile
 *  - Compiles the source code
 *  - Downloads required dependencies (if not already present)
 *  - Places compiled .class files in target/classes
 *  - Does not create a JAR
 * 
 * mvn exec:java
 *  - Runs the application using Maven
 *  - Automatically builds the runtime classpath
 *  - Includes all project dependencies
 *  - Does NOT require a runnable (fat) JAR
 * 
 * IMPORTANT:
 * ----------
 * mvn exec:java runs the application through Maven,
 * NOT through 'java -jar'.
 * 
 * In this example:
 *  - Joda-Time is an external library
 *  - Maven resolves it and adds it to the classpath 
 */

package demo;

import org.joda.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {

        LocalTime currentTime = new LocalTime();

        System.out.println("The current local time: " + currentTime);
            // The current local time: 13:39:34.973
    }
}