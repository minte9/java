/**
 * Stream filter ...
 * 
 * Strings that starts with a number (example)
 */

package com.minte9.streams.common_operations;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static void main(String[] args) {

        List<String> A, B, C;

        A = asList("1abc");
        B = asList("a", "1abc", "abc1");
        C = new ArrayList<>();

        // For
        for(String s : A) {
            if (isDigit(s.charAt(0))) {
                C.add(s);
            }
        }
        assertEquals(A, C); // pass

        // Filter
        C = B.stream()
                .filter(x -> isDigit(x.charAt(0)))
                .collect(toList());
        assertEquals(A, C); // pass
    }
}