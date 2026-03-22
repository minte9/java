/**
 * COLLECTIONS - Array vs. ArrayList
 * ---------------------------------
 * 
 * 1) Size:
 *      - Array are fixed once created
 *      - ArrayList have dynamic size
 * 
 * 2) Type of elements:
 *      - Array can store primitives (int, double, boolean, etc)
 *      - ArrayList can store only objects (wrappers of primitives are object also)
 * 
 * 3) Performance:
 *      - Array is extremely fast (best for heavy computation loops)
 *      - ArrayList slower than array, but still very fast
 */

package com.minte9.collections.lists;

import java.util.ArrayList;
import java.util.List;

public class ArrayVsArrayList {
    public static void main(String[] args) {

        // Fixed vs Dynamic
        // ----------------
        int[] arr = new int[3];
        arr[0] = 1;                  // cannot arr.add(
        System.out.println(arr[0]);  // 1

        List<String> arrayList = new ArrayList<>(); 
        arrayList.add("a");
        System.out.println(arrayList.get(0));        // a
        System.out.println(arrayList.indexOf("a"));  // 0

        // Types of elements 
        // -----------------
        int[] nums = {1, 2, 3};
        nums[0] = 4;                 // cannot nums[0] = new Object();
        System.out.println(arr[0]);  // 1

        List<Integer> numsList = new ArrayList<>();
        numsList.add(1);
        System.out.println(numsList.get(1));  // 2
        
    }
}
