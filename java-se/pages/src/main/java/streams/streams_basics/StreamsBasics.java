/**
 * STREAMS IN JAVA
 * ---------------
 * Streams were introduced in Java 8 to process collections
 * in a functional and declarative style.
 * 
 * Common operations: filter, map, collect, reduce
 * 
 * Key ideas:
 *  - A stream is not a data storage (not a collection)
 *  - It works with pipelines
 *  - Supports lazy evaluation
 *  - Encurage declarative programming
 */

package com.minte9.streams.streams_basics;

import java.util.Arrays;
import java.util.List;

public class StreamsBasics {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Jane", "Doe", "Julia");

        // Traditional way
        for (String name : names) {
            if (name.startsWith("J")) {
                System.out.println(name);  // John Jane Julia
            }
        }

        // Stream way
        names.stream()
             .filter(x -> x.startsWith("J"))
             .forEach(System.out::println);
    }
}
