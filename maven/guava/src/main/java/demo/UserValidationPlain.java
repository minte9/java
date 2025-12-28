/**
 * USER VALIDATION - PLAIN JAVA
 * ----------------------------
 * This implementation users only Java.
 * It works, but requires:
 *  - manual null checks
 *  - manual error handling
 *  - more boilerplate 
 */

package demo;

public class UserValidationPlain {
    public static boolean isValid(String username) {

        if (username == null) {
            throw new IllegalArgumentException("Username must not be null");
        }

        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty");
        }

        if (username.length() < 4) {
            return false;
        }

        return true;
    }   
}
