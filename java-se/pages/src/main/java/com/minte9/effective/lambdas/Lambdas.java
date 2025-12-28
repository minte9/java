/**
 * Java 8 Lambdas
 * 
 * We create instances of functional interfaces using ...
 * lamdba expressions.
 * 
 * Lambdas are similar to anonymous classes, but ...
 * more concise 
 */

package com.minte9.effective.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lambdas {
            
    public static void main(String[] args) {
        
        List<String> words = new ArrayList<>();
        words.add("ccc");
        words.add("a");
        words.add("bb");


        // Example 1
        Collections.sort(words,
            (s1, s2) -> Integer.compare(s1.length(), s2.length())
        );
        words.forEach(System.out::println); 
            // a, bb, ccc


        // Example 2
        words.sort(
            (s1, s2) -> Integer.compare(s1.length(), s2.length())
        );
        words.forEach(System.out::println); 
            // a, bb, ccc
    }
}