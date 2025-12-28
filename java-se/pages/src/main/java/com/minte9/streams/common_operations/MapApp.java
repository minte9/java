/**
 * Stream map (to uppercase)
 */

package com.minte9.streams.common_operations;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class MapApp {
    public static void main(String[] args) {

        List<String> A, B, C;

        A = asList("a", "b", "c");
        B = asList("A", "B", "C");
        C = new ArrayList<>();

        // For
        for(String s : A) {
            C.add(s.toUpperCase());
        }
        assertEquals(B, C); // pass

        // Stream
        C = A.stream()
            .map(x -> x.toUpperCase())
            .collect(toList());
        assertEquals(B, C); // pass
    }
}