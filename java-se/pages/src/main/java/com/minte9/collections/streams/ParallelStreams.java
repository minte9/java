/**
 * PARALEL STREAMS
 * ---------------
 * A parallel stream splits the data into multiple chunks
 * and process them in parallel using the Fork/Join Framework.
 * 
 * WHEN TO USE PARALLEL STREAMS:
 * -----------------------------
 *      - CPU-intensive operations (math, transformations)
 *      - Large databasets
 *      - Stateless functions (pure functions)
 *      - When order does NOT matter
 * 
 * WHEN "NOT" TO USE THEM:
 * -----------------------
 *      - Small lists (parallel overhead is bigger than the gain)
 *      - I/O operations (disk/network are the bottleneck, not CPU)
 *      - Stateful or shared mutable data (risk to race conditions) 
 */

package com.minte9.collections.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreams {
    public static void main(String[] args) {
        
        // Generate a list of numbers 1..20 (just for demo)
        List<Integer> numbers = IntStream.rangeClosed(1, 20)
                                         .boxed()
                                         .collect(Collectors.toList());
        
        // ============================================================
        // PARALEL STREAM EXAMPLE
        // ============================================================
        List<Integer> squared = 
            numbers.parallelStream()  // 🚀 uses multiple CPU cores
                   .filter(n -> n % 2 == 0)
                   .map(n -> slowSquare(n))  // simulate heavy work
                   .toList();  
        System.out.println("Paralel: " + squared);


        // =============================================================
        // SEQUENTIAL STREAM FOR COMPARATIION
        // =============================================================
        List<Integer> sequentialSquared = 
            numbers.stream()
                   .filter(n -> n % 2 == 0)
                   .map(n -> slowSquare(n))
                   .toList();
        System.out.println("Sequential: " + sequentialSquared);
    }   
    
    private static int slowSquare(int n) {
        try { Thread.sleep(50); } catch (InterruptedException e) {}
        return n * n;
    }

}

