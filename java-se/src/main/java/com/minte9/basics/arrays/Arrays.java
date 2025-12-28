/**
 * One way to create an array is with new operator ...
 * which allocates the memory for 3 elements
 * 
 * You can also create and initialize the array with {} syntax
 * Array length is the number of elements between braches
 */

package com.minte9.basics.arrays;

public class Arrays {
    public static void main(String[] args) {
        
        int[] nums; 
        nums = new int[2]; 
        nums[0] = 1;
        nums[1] = 2;
        
        String[] names = {"John", "Marry", "Ana"};

        System.out.println(nums[1]);  // 2
        System.out.println(names[2]); // Ana

        try {
            names[3] = "Willy";
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // Index 3 out of bounds
        }
    }
}