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
