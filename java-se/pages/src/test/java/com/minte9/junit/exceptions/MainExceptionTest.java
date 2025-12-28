/**
 * TESTING EXCEPTINOS IN main() - NO ARGUMENTS
 * -------------------------------------------
 * Applications often expect command-line arguments.
 * If none are provided, methods like args[0] may throw exceptions.
 * 
 * JUnit can verify both:
 *  - incorrect usage (exception thrown)
 *  - correct usage (normal output)
 */

package com.minte9.junit.exceptions;

import org.junit.Test;

public class MainExceptionTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void no_args_expected() {  // Expected to pass
        App.main(new String[] {});
    }

    @Test 
    public void args() {  // Expected to pass
        App.main(new String[] {"run"}); 
    }
}

class App {
    public static void main(String[] args) {
        String action = args[0];
        System.out.println(action);
    }
}