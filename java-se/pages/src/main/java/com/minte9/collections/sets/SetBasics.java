/**
 * SET BASICS - COLLECTIONS
 * ------------------------
 * Set is a Collection of UNIQUE elements
 * 
 * Key characteristics:
 *  - No duplicates allowed
 *  - No index (no get(0))
 *  - Fast lookups (especially HashSet)
 * 
 * Implementations:
 *  - HashSet (more uses), fastest, no order quaranted
 */

package com.minte9.collections.sets;

import java.util.HashSet;
import java.util.Set;

public class SetBasics {            
    public static void main(String[] args) {     

        // Create a set
        Set<String> fruits = new HashSet<>();

        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple"); // ignored
        fruits.add("Orange");
        System.out.println(fruits);  // [Apple, Banana, Orange] (no duplicate)

        // Check existance
        boolean exists = fruits.contains("Apple");  // O(1) fast
        System.out.println(exists);  // true

        // Remove elements
        fruits.remove("Orange");
        System.out.println("After removal: " + fruits);  // [Apple, Banana]

        // Iterate through elements
        for (String f : fruits) {
            System.out.println(f);
                // Apple
                // Banana
        }
    }
}
