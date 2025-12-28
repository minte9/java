/**
 * Stream collect ...
 */
package com.minte9.streams.common_operations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;

public class Collect {
    public static void main(String[] args) {

        List<String> A = Stream.of("a", "b", "c")
            .collect(Collectors.toList());
            
        assertEquals(Arrays.asList("a", "b", "c"), A); // pass
    }
}