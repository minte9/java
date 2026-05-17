## JUnit (v.4)

### 1. Basics

Java itself does not include a built-in unit testing framework in the JDK.  
The standard approach is to add a testing library such as JUnit.  
Integrate JUnit with Maven to run automated tests (pom.xml).    

~~~xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
~~~
~~~sh
mvn package
~~~

Methods annotated with @Test are invoked by the test runner.  
A single failing assertion fails the entire test method.  

~~~java
package examples.junit_v4;

import org.junit.Test;
import static org.junit.Assert.*;

public class Assertions {

    @Test
    public void basicAssertions() {
        
        // assertEquals(expected, actual)
        // ==============================
        assertEquals(4, 2 + 2);
        assertEquals("hello", "he" + "llo");

        // assertTrue / assertFalse
        // ========================
        assertTrue(10 > 1);
        assertFalse(5 > 10);

        // assertNull / assertNotNull
        // ==========================
        String a = null;
        String b = "JUnit";
        assertNull(a);
        assertNotNull(b);

        // Additional numeric assertions
        // ==============================
        assertEquals(3.14, 3.14, 0.0001);  // double comparison with delta
        assertEquals(3.14, 3.14, 0.1);
    }
}
~~~
 
Test execution:

~~~sh
mvn test
mvn -Dtest=AssertionsTestExample test

# Results :
# Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
~~~


### 2. Test Case (AAA)

A `test case` verifies one specific expected behavior.

In this example:
 - Behavior: average of squared numbers is computed correctly

We use the AAA pattern (Arrange, Act, Assert) which provides a  
clean and readable structure for writing test cases.
 
- ARRANGE: Set up objects and initial state
- ACT: Execute the method under test
- ASSERT: Verify the result matches the expectation

~~~java
package examples.junit_v4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    @Test 
    public void mytest() {  // This method is the TEST CASE
        
        // ARRANGE
        Squares squares = new Squares();
        squares.add(3);  // 9
        squares.add(5);  // 25

        // ACT
        double actual = squares.average();   // 9 + 25 = 34/2
        double expected = 17.0;

        // ASSERT
        assertEquals(actual, expected, 0.0001);  // passed
    }
}

class Squares {
    private final List<Integer> squares = new ArrayList<>();

    public void add(int x) {
        squares.add(x * x); 
    }

    public double average() { 
        return squares.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
    }
}
~~~


### 3. Annotations

JUnit provides several annotations to control test initialization and cleanup.  
Understanding these is essential for writting clean and independent test cases. 

@Before
- Runs BEFORE EACH test method
- JUnit creates a NEW instance of the test class for every test
- Use it to set up fresh objects so tests do not affect one another
 
@After
- Runs AFTER EACH test method
- Use it to clean up resources (files, connections, locks)

@BeforeClass
- Runs ONCE before all tests in the class.
- Method MUST be static.
- Use for expensive setup (database, cache, servers).
 
@AfterClass
- Runs ONCE after all tests in the class.
- Method MUST be static.
- Use for releasing shared resources.

In this example:
- The @Before method initializes the Squares object before EACH test.
- Both test gets their own fresh, independent instance.

~~~java
package examples.junit_v4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Annotations {
    
    @Before 
    public void start() {
        System.out.println("Db connection start");
    }

    @Test 
    public void run() {
        System.out.println("App run test");
    }

    @After 
    public void end() {
        System.out.println("Db connection close");
    }

    /*
        Db connection start
        App run test
        Db connection close
    */
}
~~~


### 4. Expected Exceptions

JUnit allows you to verify that a specific exceptin is thrown.

Using:
- @Test(expected = SomeException.class)  

The test PASSES if the exception is thrown.  
The test FAILS if:  

- no exception is thrown
- a different exception is thrown

This makes it easy to test error conditions or preconditions.  

~~~java
package examples.junit_v4;

import org.junit.Test;

public class ExpectedExceptions {

    @Test(expected = ArithmeticException.class)
    public void divide_by_zero_should_throw_exception() {

        Calculator calculator = new Calculator();
        calculator.divide(10, 0);
            // test passes only if ArithmeticException is thrown
    }
}

class Calculator {

    public int divide(int a, int b) {
        return a / b;
    }
}
~~~


### 5. Test Suite

Test Suite lets you group several test classes and run them as a unit.

Why a suite?

- To run related tests together (e.g., integration tests)
- To share expensive setup across multiple test classes
- To control execution order

In this example:
- @BeforeClass opens a shared "database connection"
- @AfterClass closes it
- UserTest and OrderTest both run inside the same suite

~~~java
package examples.junit_v4;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserTest.class, OrderTest.class})
public class TestSuite {

    @BeforeClass
    public static void globalSetup() {
        System.out.println("Connecting to test database...");
    }

    @AfterClass
    public static void globalTeardown() {
        System.out.println("Closing test databases...");
    }
}

/*
    Running examples.junit_v4.TestSuite
    Connecting to test database...

    UserTest.testCreateUser ....
    Imagine: insert user into mock DB
    UserTest.testDeleteUser ...
    Imagine: delete user from mock DB

    OrderTest.testPlaceOrder ...
    Imagine: insert order into mock DB

    Closing test databases...
    Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.057 sec
 */
~~~
~~~java
package examples.junit_v4;

import org.junit.Test;

public class UserTest {

    @Test
    public void testCreateUser() {
        System.out.println("UserTest.testCreateUser ....");
        System.out.println("Imagine: insert user into mock DB");
    }

    @Test
    public void testDeleteUser() {
        System.out.println("UserTest.testDeleteUser ...");
        System.out.println("Imagine: delete user from mock DB");
    }
}
~~~
~~~java
package examples.junit_v4;

import org.junit.Test;

public class OrderTest {

    @Test
    public void testPlaceOrder() {
        System.out.println("OrderTest.testPlaceOrder ...");
        System.out.println("Imagine: insert order into mock DB");
    }
}
~~~