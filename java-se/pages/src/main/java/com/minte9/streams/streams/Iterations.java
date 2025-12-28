/**
 * Streams allow us to write collections code ...
 * at a higher level of abstraction. 
 * 
 * Iterator - external iteration
 * Stream - internal interation
 */

package com.minte9.streams.streams;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Iterations {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int count = 0;

        for(int n : numbers) {
            if (n <= 2) count++;
        }
        System.out.println(count); // 2

        Iterator<Integer> it = numbers.iterator();
        while(it.hasNext()) {
            int n = it.next();
            if (n <= 2) count++;
        }
        System.out.println(count); // 4

        long total = 0;
        total = numbers.stream()
                .filter(n -> n <= 2)
                .count();
        System.out.println(total); // 2
    }
}