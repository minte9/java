/**
 * DECLARATIVE VS IMPERATIVE PROGRAMMING
 * -------------------------------------
 * 
 * IMPERATIVE:
 *      - Focuses on 'how' to do something.
 *      - Explicit loops, conditions, mutable variables.
 *      - Step-by-step instructions.
 * 
 * DECLARATIVE:
 *      - Focuses on 'what' result you want.
 *      - Hides low-level details like iteration.
 *      - Uses high-level operations: filter, map, collect, etc.
 *      - Encurages immutability and functional style.
 * 
 * STREAMS = DECLARATIVE PROCESSING
 * --------------------------------
 * Streams allow you to express business logic:
 *      "Give me all even numbers, square them, return as a list"
 * without writing explicit loops or modifying data manually.
 * 
 * WHY DECLARATIVE IS BETTER:
 * --------------------------
 *      - More readable (describes intent, not mechanism)
 *      - More reusable (operations can be extracted and tested)
 *      - Less error-prone (no indexes, no accidental mutation)
 *      - Easier to parallelize (parallelStream())
 *      - Functional pipelines are easier to maintain
 * 
 * THREE VERSIONS BELOW:
 * ---------------------
 *      1) Imperative version -> how we wrote Java before Java 8
 *      2) Declarative version -> without lambdas (using anonymous classes)
 *      3) Declarative version -> with lambdas (modern Java)
 * 
 * The problem is intentionally simple:
 *      Given a list of integers,
 *      find the square of all even numbers,
 *      return the result in a new list.
 */

package com.minte9.collections.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeclarativeProgramming {

    static List<Integer> mySquareList = new ArrayList<>();
    static List<Integer> mylist = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    public static void main(String[] args) {

        // ======================================================
        // Imperative programming (old Java style)
        // ======================================================
        for(Integer n : mylist) {
            if (n % 2 == 0) {
                mySquareList.add(n * n);
            }
        }
        System.out.println(mySquareList); // [4, 16, 36, 64, 100]


        // =====================================================
        // Declarative - without lambdas (Java 8 before lambdas)
        // =====================================================
        mySquareList = mylist.stream()
            .filter(
                new Predicate<Integer>() {
                    @Override public boolean test(Integer n) {
                        return n % 2 == 0;
                    }
                })
            .map(
                new Function<Integer, Integer>() {
                    @Override public Integer apply(Integer n) {
                        return n * n;
                    }
                }
            )
            .collect(Collectors.toList());
        System.out.println(mySquareList); // [4, 16, 36, 64, 100]


        // =======================================================
        // Declarative - with lambdas (modern and preffered)
        // =======================================================
        mySquareList = mylist.stream()
            .filter(x -> x % 2 == 0)
            .map(y -> y * y)
            .collect(Collectors.toList());
        System.out.println(mySquareList); // [4, 16, 36, 64, 100]
        
    }
}