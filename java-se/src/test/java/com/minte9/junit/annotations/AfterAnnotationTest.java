/** 
 * @After - EXECUTED AFTER EACH TEST
 * ---------------------------------
 * The @After annotation marks a method that JUnit runs "after each" 
 * individual test method completes.
 * 
 * LIFECYCLE (JUnit 4 creates a new instance per test):
 * 
 *      @Before
 *      @Test
 *      @After
 * 
 *      @Before
 *      @Test
 *      @After
 * 
 * WHY USE @After?
 * ---------------
 * It is ideal for cleanup operations such as:
 *  - closing database connections
 *  - delete temporary files
 *  - resetting shared state
 *  - releasing external resources
 */

package com.minte9.junit.annotations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AfterAnnotationTest {
    
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
