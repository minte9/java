/**
 * STREAMS - MAP
 * --------------
 * Map each value to uppercase.
 */

package com.minte9.streams.common_operations;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class StreamMap {
    public static void main(String[] args) {

        List<String> source = asList("a", "b", "c");
        List<String> expected = asList("A", "B", "C");
        
        assertEquals(expected, toUpper(source));        // pass
        assertEquals(expected, toUpperStream(source));  // pass

        System.out.println("Done");
    }

    private static List<String> toUpper(List<String> lst) {
        List<String> result = new ArrayList<>();
        for (String s: lst) {
            result.add(s.toUpperCase());
        }
        return result;
    }

    private static List<String> toUpperStream(List<String> lst) {
        return lst.stream()
                  .map(x -> x.toUpperCase())  // lambda OR
                  .map(String::toUpperCase)   // method reference
                  .collect(toList());
    }
}