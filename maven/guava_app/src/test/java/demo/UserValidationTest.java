/**
 * VALIDATION TEST- JUNIT TEST
 * ---------------------------
 * Same test logic applies to both implementations.
 * This highlights that Guava improves code quality, not behavior.
 */

package demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserValidationTest {
    
    @Test
    public void validUsername() {
        assertTrue(UserValidationGuava.isValid("admin"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullUsername() {
        UserValidationGuava.isValid(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyUsername() {
        UserValidationGuava.isValid("");
    }
}
