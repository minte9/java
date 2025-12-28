/**
 * STREAM API (Java 8+)
 * --------------------
 * Streams allow functional-style bulk operations on data:
 *      - filtering
 *      - mappging
 *      - sorting
 *      - reducing (sum, average, min, max) 
 * 
 * IMPORTANT:
 * ---------
 * A stream is NOT a collection.
 * It does not store elements, it process them.
 * 
 * Streams can be created from:
 *      - collections
 *      - arrays (Arrays.stream())
 *      - static stream buileder (Stream.of())
 *      - files, regex, generators, etc
 * 
 * OPERATIONS:
 * -----------
 * A stream pipeline has:
 *      1) Source
 *      2) Intermediate operations (filter, map, sorted)
 *      3) Terminal operation (forEach, collect, reduce)
 * 
 * Streams can run:
 *      - sequentially (default)
 *      - in paralel (parallelStream())
 */

package com.minte9.collections.streams;

import java.util.List;

public class Streams {
    public static void main(String[] args) {

        List<Order> orders = List.of(
            new Order("Laptop", 1200),
            new Order("Mouse", 25),
            new Order("Keyboard", 35)
        );

        double total = orders.stream()
                .mapToDouble(Order::price)
                .sum();
            
        System.out.println(total);  // 1260.0
    }
}

record Order(String name, double price) {}  // Java 17+
