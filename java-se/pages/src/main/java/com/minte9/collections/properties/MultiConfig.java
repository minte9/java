/**
 * Loading multiple configuration files.
 * 
 * Folder (classpath):
 *  src/main/resources/
 *      database-dev.properties
 *      database-prod.properties
 */

package com.minte9.collections.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MultiConfig {
    public static void main(String[] args) throws IOException {
        
        // Determine environment (dev / prod)
        String env = System.getProperty("env", "dev");  // default 'dev'
        System.out.println("Environment: " + env);  // dev

        // Load properties
        Properties props = new Properties();
        loadProps(props, "database-" + env + ".properties");

        // Use properties
        System.out.println(props.getProperty("db.url"));  // localhost
        System.out.println(props.getProperty("db.user"));  // dev_user
    }    

    private static void loadProps(Properties props, String filename) throws IOException {
        InputStream input = MultiConfig.class
            .getClassLoader()
            .getResourceAsStream(filename);

        if (input == null) {
            throw new RuntimeException("Propertis file not found: " + filename);
        }
        props.load(input);
    }
}
