/**
 * HashMap stores key-value pairs.
 * 
 * The keys must be UNIQUE, values may be DUPLICATES.
 * If we put() a value using a key that alreasy exists,
 * the old value is overwritten.
 * 
 * Internaly HashMap uses hashing to store the items in buckets.
 *      - hashCode(key) to finds the bucket
 *      - equals(key) to resolve collisions (same bucket)
 * 
 * Keys must implement proper equals() and hashCode()
 * (String already does).
 * 
 * Performance:
 *      - put() and get() are O(1) average time
 *      - very fast for lookups by key
 */

package com.minte9.collections.maps;
import java.util.HashMap;
import java.util.Map;

public class HashMaps {
    public static void main(String[] args) {
    
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Mary", 10);
        scores.put("Mary", 20); // overrites value for key "Mary"
        scores.put("John", 20);
        scores.put("Boby", 16);

        System.out.println(scores); // {John=20, Boby=16, Mary=20}
        System.out.println(scores.get("John"));  // 20
    }
}
