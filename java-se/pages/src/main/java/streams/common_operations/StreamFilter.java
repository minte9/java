/**
 * STREAMS - FILTER
 * ----------------------------------------------
 * Filter only strings that starts with a number.
 */

package com.minte9.streams.common_operations;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class StreamFilter {
    public static void main(String[] args) {

        List<String> source = asList("a", "1abc", "abc1");
        List<String> expected = asList("1abc");
        
        assertEquals(expected, isDigitLoop(source));    // pass
        assertEquals(expected, isDigitStream(source));  // pass

        System.out.println("Done");
    }

    private static List<String> isDigitLoop(List<String> lst) {
        List<String> result = new ArrayList<>();

        for (String s: lst) {
            if (isDigit(s.charAt(0))) {
                result.add(s);
            }
        }
        return result;
    }

    private static List<String> isDigitStream(List<String> lst) {
        return lst.stream()
                  .filter(x -> isDigit(x.charAt(0)))  // Look Here
                  .collect(toList());
    }
}