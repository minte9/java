/**
 * How to iterate (loop) through a Map.
 * 
 * Java does not support Javascript style object literals.
 *      let map = { a:1, b:2 }  // ❌ NOT valid in Java
 * 
 * But Java has alternatives to initialize a map without put().
 *      Map.of() (Java 9+) 
 * 
 * keySet()   -> iterate through keys only
 * values()   -> iterate through values only
 * entrySet() -> iterate through key/value pairs
 */

package com.minte9.collections.maps;

import java.util.HashMap;
import java.util.Map;

public class Loops {
    public static void main(String[] args) {
    
        Map<String, Integer> scores = Map.of(  // Java 9+
            "Mary", 10,
            "John", 20,
            "Boby", 16
        );

        // Using put() (most common)
        scores = new HashMap<>();
        scores.put("Mary", 10);
        scores.put("John", 20);
        scores.put("Boby", 16);

        // Loop 1: keys only
        for (String key : scores.keySet()) {
            System.out.println(key);  // John Mary Boby
        }

        // Loop 2: values only
        for (Integer value : scores.values()){
            System.out.println(value);  // 20 16 10
        }

        // Loop 3: both keys and values
        for(Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
                // John 20
                // Boby 16
                // Mary 10
        }
    }
}
