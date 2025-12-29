/**
 * STREAM - REDUCE
 * ------------------------------------------------------------
 * Reduce is used when you want a single result from collection.
 * For example, to calculate the sum.
 * 
 * Imperative approach - using for loop
 * Declarative approach - acc is the "accumulator" (current sum)
 */

package com.minte9.streams.common_operations;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {
    public static void main(String[] args) {

        List<Integer> source = Arrays.asList(1, 2, 3);
        int expected = 6;

        assertEquals(expected, reduceToSumLoop(source));
        assertEquals(expected, reduceToSumStream(source));
        
        System.out.println("Done");
    }

    public static int reduceToSumLoop(List<Integer> lst) {
        int sum = 0;
        for (Integer i : lst) {
            sum += i;
        }
        return sum;
    }

    public static int reduceToSumStream(List<Integer> lst) {
        return lst.stream()
                  .reduce(0, (acc, x) -> acc + x);
    }
}
