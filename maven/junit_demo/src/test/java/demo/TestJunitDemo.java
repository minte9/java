/**
 * JUNIT WITH MAVEN - BASIC TEST
 * -----------------------------
 * This example demonstrates how Maven integrates with JUnit
 * to run automated tests.
 * 
 * KEY CONCEPT:
 * ------------
 *  - JUnit is a testing framework
 *  - Maven runs tests automatically during the build lifecycle
 *  - Tests are located in: src/test/java
 * 
 * MAVEN COMMANDS:
 * ---------------
 * mvn test
 *  - Compiles main code and test code
 *  - Executes all test classes
 *  - Fails the build if any test fails
 * 
 * mvn package
 *  - Run tests FIRST
 *  - Then package the application
 *  - Build fails if tests fails
 * 
 * TEST DISCOVERY RULE:
 * --------------------
 * Maven automatically runs test classes named:
 *   *Test.java
 *   Test*.java
 *   *TestCase.java
 */

package demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestJunitDemo {

    @Test
    public void testSum() {

        // ARANGE
        int a = 5;
        int b = 10;

        // ACT
        int result = a + b;

        // ASSERT
        assertEquals(15, result);
    }
}