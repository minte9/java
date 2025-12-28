/**
 * USER VALIDATION - GUAVA VERSION
 * -------------------------------
 * This implementation uses Google Guava utilities.
 * 
 * BENEFITS:
 * ---------
 *  - Fail-fast validation
 *  - Clear intent
 *  - Less boilerplate
 *  - Easier to maintain 
 */

package demo;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class UserValidationGuava {
    
    public static boolean isValid(String username) {

        Preconditions.checkArgument(
            !Strings.isNullOrEmpty(username),
            "Username must not be null or empty"
        );

        return username.length() >= 4;
    }
}
