/**
 * INTORDUCTION TO JUNIT (Junit 4 style)
 * -------------------------------------
 * JUnit is the standard unit-testing framework for java.
 * A test method is any public method annotated with @Test.
 * 
 * Test execution:
 * - IDEs (Eclipse/ItelliJ/VSCode) detect test classes automatically
 * - Methods annotated with @Test are invoked by the test runner
 * - A single failing assertion fails the entire test method
 */

package com.minte9.junit.introduction;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testBasicAssertions() {
        
        // ------------------------------
        // assertEquals(expected, actual)
        // ------------------------------
        assertEquals(4, 2 + 2);
        assertEquals("hello", "he" + "llo");

        // -------------------------------
        // assertTrue / assertFalse
        // -------------------------------
        assertTrue(10 > 1);
        assertFalse(5 > 10);

        // -------------------------------
        // assertNull / assertNotNull
        // -------------------------------
        String a = null;
        String b = "JUnit";

        assertNull(a);
        assertNotNull(b);

        // -------------------------------
        // Additional numeric assertions
        // -------------------------------
        assertEquals(3.14, 3.14, 0.0001);  // double comparison with delta
        assertEquals(3.14, 3.14, 0.1);
    }
}
