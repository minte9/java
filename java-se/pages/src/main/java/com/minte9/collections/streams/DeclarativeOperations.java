/**
 * Declarative operations - reusability + testability
 * 
 * This class demonstrates how declarative programming enables
 * clean, reusable, and independently testable business logic.
 * 
 * This file pairs with DeclarativeOperationsTest.java,
 * which proves reusability through unit tests.
 */

package com.minte9.collections.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeclarativeOperations {

    // Reusable predicate (testable)
    public static final Predicate<Integer> IS_EVEN = 
        n -> n % 2 == 0;

    // Reusable function (testable)
    public static final Function<Integer, Integer> SQUARE = 
        n -> n * n;

    // Reusable and testable stream pipeline
    public static List<Integer> filterAndSquare(List<Integer> list) {
        return list.stream()
                   .filter(IS_EVEN)
                   .map(SQUARE)
                   .collect(Collectors.toList());
    };

    public static void main(String[] args) {
        List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(filterAndSquare(myList));  // [4, 16, 36, 64, 100]
    }    
}
