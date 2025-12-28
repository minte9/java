/**
 * ArrayList allows us to dynamicaly resize the array when needed.
 * It has operations like `add()`, `remove()`, `get()`, `contains()`.
 * Use `size()` to get the length.
 */

package com.minte9.collections.lists;

import java.util.ArrayList;

public class ArraysList {
    public static void main(String[] args) {

        // We DON'T know how many grades the student will have
        ArrayList<Integer> grades = new ArrayList<>();

        // Add values dinamically
        grades.add(85);
        grades.add(90);
        grades.add(78);

        System.out.println("Initial: " + grades);  // Initial: [85, 90, 78]
        System.out.println("Size: " + grades.size());  // Size: 3

        // Add more later
        grades.add(92);
        grades.add(88);

        System.out.println("Later: " + grades);  // Later: [85, 90, 78, 92, 88]
        System.out.println("Size: " + grades.size());  // Size: 5

    }
}
