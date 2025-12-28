/**
 * TEST CASE - LAMBDAS + FUNCTINAL INTERFACE EXAMPLE
 * -------------------------------------------------
 * This example shows how JUnit can be used to test code that relies
 * on functional interfaces and lambda expressions.
 * 
 * KEY CONCEPTS:
 * -------------
 * 1) A @FunctionalInterface is an interface with exactly ONE abstract method.
 *    It is designed to be implemented using a lambda expression.
 * 
 * 2) Lambdas provide behavior as a value:
 *    () -> 3 * 3
 * 
 * 3) A test case can verify not only data but also behavior supplied via lambdas.
 * 
 * WHAT THIS TEST CHECKS:
 * ----------------------
 * - Two lambda functions representing "squaring operations" are stored.
 * - Each lambda is executed via Square::get.
 * - The average of all computed squares is correctly returned.
 */

package com.minte9.junit.test_case;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;


public class LambdasTest {
    
    @Test 
    public void mytest() {

        // ARRANGE
        SQ squares = new SQ();
        squares.add(() -> 3 * 3);  // lambda for 9
        squares.add(() -> 5 * 5);  // lambda for 25

        // ACT
        int actual =  squares.average();  // (9 + 25) / 2 = 17


        // ASSERT
        assertEquals(actual, 17);  // passed
    }
}

class SQ {

    private List<Square> squares = new ArrayList<>();

    public void add(Square s) { 
        squares.add(s); 
    }

    public int average() {
        int total = squares.stream()
                           .mapToInt(Square::get) // execute lambda
                           .sum();
        return total / squares.size();
    }
}

// Functional interface: designed for lambdas
@FunctionalInterface 
interface Square {
    int get(); 
}
