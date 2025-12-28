/**
 * Variables in Java are passed by copy, 
 * not by reference
 */

package com.minte9.basics.variables;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PassedByValueTest {

    @Test public void objectTest() {

        A obj = new A();

        obj.x = 3;
        assertTrue(obj.x == 3); // pass
        
        obj.init();
        assertTrue(obj.x != 3); // pass
    }
}

class A {

    int x;
    public void init() {
        x = 0;
    }
}