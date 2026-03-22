/**
 * ARRAYLIST EXAMPLES - COLLECTIONS
 * --------------------------------
 */

package com.minte9.collections.lists;

import java.util.ArrayList;
import java.util.List;

public class ListExamples {
    public static void main(String[] args) {

        // GRADES
        // -------------------------------------
        // Dynamic size
        // -------------------------------------
        List<Integer> grades = new ArrayList<>();
        grades.add(85);
        grades.add(90);
        grades.add(78);

        System.out.println("Initial: " + grades);       // [85, 90, 78]
        System.out.println("Size: " + grades.size());   // Size: 3

        // Add more letter (grows as needed)
        grades.add(92);
        grades.add(88);

        // Parsing loop
        for (int g : grades) {
            System.out.print(g + " ");                  // 85 90 78 92 88
        }
        
        // Read and modify elements
        int last = grades.get(grades.size() - 1);       // 88
        grades.set(grades.size() - 1, last + 12);       // 100

        System.out.println("Later: " + grades);         // [85, 90, 78, 92, 100]
        System.out.println("Size: " + grades.size());   // 5

        // Check contain and remove
        if (grades.contains(90)) {
            grades.remove(Integer.valueOf(90));             // remove by value
        }
        System.out.println("After remove(90): " + grades);  // [85, 78, 92, 100]

        // Remove by index
        grades.remove(0); // remove first
        System.out.println("After remove(0): " + grades);  // [78, 92, 100]
    }
}
