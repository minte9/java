/**
 * Maven dependency test (junit)
 * 
 * mvn package
 * mvn test
 */

package com.minte9.junit;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemoJunitTest {

    @Test public void testSum() {

        int a = 5;
        int b = 10;

        assertEquals(15, a+b);
    }
}