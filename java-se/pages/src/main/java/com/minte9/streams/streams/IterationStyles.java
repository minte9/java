/**
 * STREAMS - ITERATION STYLES
 * --------------------------
 * Streams allow us to write collection-processing code 
 * at a higher level of abstraction.
 * 
 * TWO APPROACHES:
 * ---------------
 * 1) External iteration (imperative)
 *      - The developer controls HOW iteration happens 
 *      - Uses loops or iterators
 *      - Requires mutable state (counter, variables)
 * 
 * 2) Internal iteration (declarative)
 *      - The library controls HOW iteration hapens
 *      - The developer specifies WHAT should be done
 *      - No explicit loops or mutable counters
 * 
 * Iterator - external iterator
 * Stream   - internal iterator
 */

package com.minte9.streams.streams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterationStyles {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // -------------------------------------
        // 1) External iteration - for-each loop
        // -------------------------------------
        int count = 0;

        for (int n : numbers) {
            if (n <= 2) {
                count++;
            }
        }
        System.out.println(count);  // 2

        // -------------------------------------
        // 2) External iteration - Iterator
        // -------------------------------------
        count = 0;

        Iterator<Integer> it = numbers.iterator();
        while(it.hasNext()) {
            int n = it.next();
            if (n <= 2) {
                count++;
            }
        }
        System.out.println(count);  // 2

        // -------------------------------------
        // 3) Internal iteration - Stream
        // -------------------------------------
        long total = numbers.stream()
                            .filter(n -> n <= 2)
                            .count();
        System.out.println(total);  // 2
    }    
}
