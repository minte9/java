/**
 * The stream library was design to iterate only once 
 * (lazy evaluation)
 * 
 * Methods that generate a final value out of the Stream sequence ...
 * are called "eager"
 * 
 * Code without an eager method doesn't print anything ...
 * due to lazy evaluation
 */

package com.minte9.streams.streams;
import java.util.Arrays;
import java.util.List;

public class Lazy {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3);

        numbers.stream().filter(n -> {
            System.out.println(n);
            return n <= 2;
        }); // nothing

        numbers.stream().filter(n -> {
            System.out.println(n);
            return n <= 2;
        }).count(); // 1 2 3 - Look Here
    }
}