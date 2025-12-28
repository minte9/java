/**
 * Java 8 Lambdas
 * 
 * Anonymous class instance as a function object is ...
 * hard to use.
 */

package com.minte9.effective.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Anonymous {
            
    public static void main(String[] args) {
        
        List<String> words = new ArrayList<>();
        words.add("ccc");
        words.add("a");
        words.add("bb");

        Collections.sort(words, 

            new Comparator<String>() { // - Look Here

                @Override public int compare(String s1, String s2) {
                    return Integer.compare(
                        s1.length(), s2.length()
                    );
                }
            }
        );
        words.forEach(System.out::println);
            // a, bb, cc
    }
}