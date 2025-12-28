/** 
 * JUNIT LIFECYCLE ANNOTATIONS
 * ---------------------------
 * JUnit provides several annotations to control test initialization
 * and cleanup. Understanding these is essential for writting clean and
 * independent test cases.
 * 
 * @Before
 *  - Runs BEFORE EACH test method.
 *  - JUnit creates a NEW instance of the test class for every test.
 *  - Use it to set up fresh objects so tests do not affect one another.
 * 
 * @After
 *  - Runs AFTER EACH test method.
 *  - Use it to clean up resources (files, connections, locks).
 * 
 * @BeforeClass
 *  - Runs ONCE before all tests in the class.
 *  - Method MUST be static.
 *  - Use for expensive setup (database, cache, servers).
 * 
 * @AfterClass
 *  - Runs ONCE after all tests in the class.
 *  - Method MUST be static.
 *  - Use for releasing shared resources.
 * 
 * In this example:
 *  - The @Before method initializes the Squares object before EACH test.
 *  - Both test gets their own fresh, independent instance.
 */

package com.minte9.junit.annotations;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class BeforeAnnotationTest {

    private Squares squares;

    @Before 
    public void executedBeforeEach() {

        squares = new Squares();
        squares.add(3);  // 9
        squares.add(5);  // 25

        System.out.println("Squares initialized!");  
        // Prints twice (once per each test)
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

class Squares {

    private final List<Integer> squares = new ArrayList<>();

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
