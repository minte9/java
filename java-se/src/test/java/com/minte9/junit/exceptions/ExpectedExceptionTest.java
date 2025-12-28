/**
 * EXPECTED EXCEPTIONS IN JUNIT 4
 * ------------------------------
 * JUnit allows you to verify that a specific exceptin is thrown.
 * 
 * Using:
 *      @Test(expected = SomeException.class)
 * 
 * The test PASSES if the exception is thrown.
 * The test FAILS if:
 *  - no exception is thrown
 *  - a different exception is thrown
 * 
 * This makes it easy to test error conditions or preconditions.
 */

package com.minte9.junit.exceptions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class ExpectedExceptionTest {

    @Test(expected = ArithmeticException.class)
    public void no_squares_expected() {
        Squares squares = new Squares();
        squares.average();  // division by zero (exception thrown) - test passes
    }

    @Test public void average() {
        Squares squares = new Squares();
        squares.add(3); // 9
        squares.add(5); // 25
        assertEquals(squares.average(), 17);
    }
}

class Squares {
    private List<Integer> squares = new ArrayList<>();

    public void add(int x) {
        squares.add(x * x); 
    }

    public int average() throws ArithmeticException {
        int total = squares.stream().mapToInt(Integer::intValue).sum();
        return total / squares.size();
    }
}