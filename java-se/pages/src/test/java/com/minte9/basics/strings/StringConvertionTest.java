/**
 * Conveting Strings to Integers and vice versa.
 */

package com.minte9.basics.strings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class StringConvertionTest {

    Integer a = Integer.valueOf("1");  // 1
    Float   b = Float.valueOf("2.22"); // 2.22
    int     x = 11;
    
    @Test public void toNumberTest() {
        assertEquals(a, 1, 0);
        assertNotEquals(a, "1");
        assertEquals(b, 2.23, 0.1);
        assertNotEquals(b, 2.23, 0);
    }

    @Test public void toStringTest() {
        assertEquals("11", "" + x);
        assertEquals("11", "" + String.valueOf(x));
        assertEquals("11", "" + Integer.toString(x));
    }
}