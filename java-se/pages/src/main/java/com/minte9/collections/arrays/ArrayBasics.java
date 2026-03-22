/**
 * ARRAYS in JAVA
 * --------------
 * Arrays in Java have fixed size.
 * They can store either primitives or objects (wrappers unboxing).
 * 
 * Arrays are low-level structures, very fast.
 * Arrays are less flexible than ArrayList.
 * 
 * Collections:
 *  - Arrays.copyOf() creates a new array, not just a new reference.
 */

package com.minte9.collections.arrays;

import java.util.Arrays;

public class ArrayBasics {
    public static void main(String[] args ) {

        // Create and populate
        String[] arr = new String[3];
        arr[0] = "a";
        arr[1] = "b";
        arr[2] = "c";
        
        // Get element (by index)
        String first = arr[0];
        System.out.println("First " + first);  // a

        // Set element
        arr[1] = "x";
        System.out.println("Changed " + arr[1]);  // x

        // Iterate using enhanced for loop
        for (String s : arr) {
            System.out.println(s);  // a x c
        }

        // Size
        int size = arr.length;
        System.out.println("Length " + size);  // 3

        // Object unboxing
        int[] nums = {1, 2, 3, 0};
        nums[2] = 4;
        nums[3] = Integer.parseInt("4");
        System.out.println(nums[3]);  // 4

        // Copy arrays safely
        int[] copy = Arrays.copyOf(nums, nums.length);

        // Chaning the copy does not affect the original
        copy[0] = 100;  
        System.out.println("Original: " + nums[0]);  // 1

        // Changing the original does not affect the copy
        nums[0] = 999;
        System.out.println("Copy: " + copy[0]);  // 100
    }
}
