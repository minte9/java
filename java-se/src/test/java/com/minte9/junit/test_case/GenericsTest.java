/**
 * TEST CASES WITH GENERICS
 * ------------------------
 * This example demonstrates how to write JUnit test cases for a
 * class that uses Java Generics, specificlly a slit that accepts
 * only numeric types (T exntends Number).
 * 
 * WHAT WE ARE TESTING:
 * --------------------
 * 1. That MyList<Integer> correctly sums integer values.
 * 2. That MyList<Double> correctly sums double values (via intValue()).
 * 3. That the sum is returned as an int, regardless of numeric type.
 * 4. That type safety prvents invalid comaparitions (comparing to a String).
 * 
 * WHY T EXTENDS NUMBER?
 * ---------------------
 * By restricting the type parameter to subclasses of Number, we gain
 * access to Number methods such as intValue(), doubleValue(), etc.
 * This makes it possible to process generically-typed numeric values.
 * 
 * JUNIT ASSERTIONS:
 * -----------------
 * assertEquals(expected, actual)
 * assertNotEquals(unexpected, actual)
 */

package com.minte9.junit.test_case;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.List;


public class GenericsTest {

    @Test 
    public void integers() {
        
        MyList<Integer> numbers = new MyList<>();
        numbers.add(3);
        numbers.add(5);

        assertEquals(numbers.sum(), 8);  // expected, actual
    }

    @Test 
    public void doubles() {
        
        MyList<Double> numbers = new MyList<>();
        numbers.add(3.0);
        numbers.add(5.0);

        assertEquals(numbers.sum(), 8);  // sum() returns int
        assertNotEquals(numbers.sum(), "8.0");  // not equal to string
    }
}

class MyList<T extends Number> {

    private List<T> items = new ArrayList<>();

    public void add(T t) {
        items.add(t); 
    }

    public int sum() { 
        return items.stream()
                    .mapToInt(Number::intValue)
                    .sum();
    }
}
