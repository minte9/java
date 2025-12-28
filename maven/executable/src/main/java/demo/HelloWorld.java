/**
 * MAVEN BUILD - EXECUTABLE (FAT JAR)
 * ----------------------------------
 * This example demonstrates how to build a FAT jar using Maven.
 * 
 * A fat JAR:
 * ----------
 *  - Contains application classes
 *  - Contains ALL external dependencies
 *  - can be executed directly wiht java -jar 
 * 
 * KEY MAVEN COMMANDS:
 * -------------------
 * mvn dependency:tree
 * mvn versions:display-dependency-updates
 * 
 * EXTERNAL LIBERY USED:
 * ---------------------
 * Apache Common Lang (StringUtils)
 */

package demo;

import org.apache.commons.lang3.StringUtils;

public class HelloWorld {
    public static void main(String[] args) {
        
        String text = "hello maven";
        String result = StringUtils.capitalize(text);

        System.out.println(result);
            // Hello maven
    }
}
