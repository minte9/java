package examples.junit;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    @Test 
    public void mytest() {  // This method is the TEST CASE
        
        // ARRANGE
        Squares squares = new Squares();
        squares.add(3);  // 9
        squares.add(5);  // 25

        // ACT
        double actual = squares.average();   // 9 + 25 = 34/2
        double expected = 17.0;

        // ASSERT
        assertEquals(actual, expected, 0.0001);  // passed
    }
}

class Squares {
    private final List<Integer> squares = new ArrayList<>();

    public void add(int x) {
        squares.add(x * x); 
    }

    public double average() { 
        return squares.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
    }
}