/**
 * ARRAYLIST BASICS - COLLECTIONS
 * ------------------------------
 * List is the interface. 
 * ArrayList is an implementation.
 * 
 * ArrayList:
 *  - A resizable array-backed list (grows/shrinks as needed).
 * 
 * Common operations:
 *  - add(E e), add(int index, E e), remove(int index), remove(Object o)
 *  - get(int index), set(int index, E e)
 *  - contains(Object o), indexOf(Object o)
 * 
 * Loops:
 *  - Get the list size: arr.size()
 * 
 * Types:
 *  - Stores objects (reference type)
 *  - For primitives, use wrapper types (Integer, Double)
 *  - Autoboxing convert int -> Integer automatically when adding.
 * 
 * Time Complexity:
 *  - Index access: O(1)
 *  - Insertion/Removal at end: O(1)
 *  - Insertion/Removal in the middle: O(n) due to shifts
 * 
 * Common Pitfalls:
 *  - Using primitives directly is not allowed (use Integer, not int).
 */

package com.minte9.collections.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListBasics {
   public static void main(String[] args) {
       
        // Create and populate
        List<String> A = new ArrayList<>();
        A.add("a");
        A.add("b");
        A.add("b");  // duplicates are allowed
        A.add("c");
        A.add("d");

        // Iterate using enhanced for loop
        for (String s : A) {
            System.out.print(s + " ");  // a b b c d 
        }

        // Remove element by value OR index
        A.remove("b");
        A.remove(2);
        System.out.println(A);  // a, b, d]

        // Check if element exists
        Boolean exists = A.contains("c");
        System.out.println("c exists = " + exists); // true

        // Get element by index
        String first = A.get(0);
        System.out.println("Fist is " + first);  // a

        // Find the index
        int index = A.indexOf("c");
        System.out.println("Index of c " + index);  // 1

        // Get sublist
        List<String> sublist = A.subList(0, 2);
        System.out.println("Sublist " + sublist);  // [a, c]

        // Get size
        int size = A.size();
        System.out.println("Size = " + size);  // 2

        // Sort
        A.add("b");
        Collections.sort(A);
        System.out.println(A);  // [a, b, b, d]


        // CONVERSION
        // ---------------------------
        String[] arr = {"A", "B", "C"};            // size fixed

        // Array to fixed-size List view
        List<String> fixed = Arrays.asList(arr);   // size fixed
        fixed.set(1, "B+");                        // OK: writes back into arr
            // fixedView.add("D");                 // ❌ UnsupportedOperationException
        System.out.println("Array after set via view: " 
            + Arrays.toString(arr)                  // [A, B+, C]
        );  
         
        // Array to resizable List
        List<String> resizable = new ArrayList<>(fixed);
        resizable.add("D");
        System.out.println("Resizable list: " + resizable);  // [A, B+, C, D]
   } 
}
