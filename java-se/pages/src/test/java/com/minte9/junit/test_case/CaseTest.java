/**
 * TEST CASE - Example using AssertEquals
 * -----------------------------------------------------
 * A "test case" verifies one specific expected behavior.
 * 
 * In this example:
 *  - Behavior: average of squared numbers is computed correctly
 * 
 * We use the AAA pattern (Arrange, Act, Assert) which provides a 
 * clean and readable structure for writing test cases.
 * 
 *  ARRANGE: Set up objects and initial state
 *  ACT:     Execute the method under test
 *  ASSERT:  Verify the result matches the expectation
 */

package com.minte9.junit.test_case;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class CaseTest {

    @Test 
    public void mytest() {  // This method is the TEST CASE
        
        // ARRANGE
        Squares squares = new Squares();
        squares.add(3);  // 9
        squares.add(5);  // 25

        // ACT
        int actual = squares.average();   // 9 + 25 = 34/2
        int expected = 17;

        // ASSERT
        assertEquals(actual, expected);  // passed
    }
}

class Squares {

    private final List<Integer> squares = new ArrayList<>();

    public void add(int x) {
        squares.add(x * x); 
    }

    public int average() { 
        int total = squares.stream()
                           .mapToInt(Integer::intValue)
                           .sum();
        return total / squares.size(); 
    }
}
