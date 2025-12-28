/**
 * Stream reduce ...
 * 
 * Wwhen you want a single result from collection.
 * Calculate the sum (example)
 * 
 * Imperative - using for loop
 * Declarative - acc is the "accumulator" (current sum)
 */

package com.minte9.streams.common_operations;

import static org.junit.Assert.assertEquals;
import java.util.stream.Stream;
import static java.util.Arrays.asList;

public class Reduce {
    public static void main(String[] args) {

        int sum = 0;
        for(Integer i : asList(1, 2, 3)) {
            sum += i;
        }
        assertEquals(6, sum);

        int sum2 = Stream.of(1, 2, 3)
            .reduce(0, (acc, x) -> acc + x);
        assertEquals(6, sum2);
    }
}
