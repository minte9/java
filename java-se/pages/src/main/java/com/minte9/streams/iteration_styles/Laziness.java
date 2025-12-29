/**
 * STREAMS - LAZY EVALUATION
 * -------------------------
 * Streams are evaluated lazily.
 * 
 * KEY IDEAS:
 * ----------
 *  - A stream does NOT process data when it is created
 *  - Intermediate operations (filter, map) are lazy
 *  - Nothing happens until a TERMINAL operation is invoked
 * 
 * TERMINAL OPERATIONS:
 * --------------------
 *  - count()
 *  - forEach()
 *  - collect()
 *  - findFirst()
 *  - anyMatch()
 * 
 * Without a terminal operation, a stream does nothing.
 * 
 * When we write numbers.strea().filter(...) we are only 
 * describing a pipeline.
 */

package com.minte9.streams.streams;

import java.util.Arrays;
import java.util.List;

public class Laziness {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // ---------------------------------------
        // No terminal operation - nothing happens
        // ---------------------------------------
        numbers.stream()
               .filter(n -> {
                    System.out.println("filter: " + n);
                    return n <= 2;
               });
        // No output due to lazy evaluation

        // --------------------------------------
        // Terminal operation triggers evaluation
        // --------------------------------------
        numbers.stream()
               .filter(n -> {
                    System.out.println("filter: " + n);
                    return n <= 2;
               })
               .count();
        // Output:
        // filter: 1
        // filter: 2
        // filter: 3        
    }
}
