/**
 * Loading a properties file (best practice)
 * 
 * Configuration files should be placed in:
 *      src/main/resources
 * 
 * Example:
 *      src/main/resources/config.properties
 * 
 * Maven/Gradle automatically puts everything in /resources
 * onto the application's CLASSPATH - even inside a JAR.
 * 
 * Classpath loading works everywhere, we can get it using:
 *      Config.class.getClassLoader().getResourceAsStream()
 */

package com.minte9.collections.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static void main(String[] args) throws IOException {

        InputStream input = Config.class
            .getClassLoader()
            .getResourceAsStream("config.properties");

        if (input == null) {
            throw new RuntimeException("config.properties not found");
        }

        Properties props = new Properties();
        props.load(input);
        props.list(System.out);

            /**
             * db.user=myuser
             * db.pass=mypassword
             * db.port=9000
             * db.url=localhost
             */

        System.out.println(props.getProperty("db.user"));  // myuser
        System.out.println(props.getProperty("db.port"));  // 9000
    }
}