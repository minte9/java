/**
 * @BeforeClass - RUNCE ONCE BEFORE ALL TEST
 * -----------------------------------------
 * JUnit provides @BeforeClass for expensive setup operations that should
 * run only a single time before any test methods are executed.
 * 
 * In this example:
 *  - Square collection is created ONLY ONCE.
 *  - Both tests use the same initialized data.
 */

package com.minte9.junit.annotations;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class BeforeClassAnnotationTest {

    private static SQ squares;

    @BeforeClass 
    static public void executedBeforeOnce() {
        
        squares = new SQ();
        squares.add(3);  // 9
        squares.add(5);  // 25

        System.out.println("Squares initialized!");
        // Exectuted once for the entire class
    }
    
    @Test 
    public void average() {
        assertEquals(squares.average(), 17);
    }

    @Test 
    public void sum() {
        assertEquals(squares.sum(), 34);
    }
}

class SQ {

    private List<Integer> squares = new ArrayList<>();

    public void add(int x) {
        squares.add(x * x); 
    }

    public int average() { 
        return sum() / squares.size(); 
    }

    public int sum() { 
        return squares.stream().mapToInt(Integer::intValue).sum();
    }
}
