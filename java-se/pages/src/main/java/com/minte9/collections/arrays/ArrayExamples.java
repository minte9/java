/**
 * ARRAYS EXAMPLES
 * ---------------
 */
package com.minte9.collections.arrays;

public class ArrayExamples {
    public static void main(String[] args) {

        // GRADES - Fixed number of grades - exactly 5
        // -------------------------------------------
        int[] grades = new int[5];
        grades[0] = 85;
        grades[1] = 90;
        grades[2] = 78;
        grades[3] = 92;
        grades[4] = 88;
        System.out.println(
            "Exam grades: " + grades.length  // Exam grades: 5
        );
        for (int g : grades) {
            System.out.print(g + " ");  // 85 90 78 92 88
        }
        
        // WEEK DAYS - Fixed numer of week days
        // ------------------------------------
        String days[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        System.out.println(
            "Week days: " + days.length // Week days: 7
        );
        for (String d : days) {
            System.out.print(d + " ");  // Mon Tue Wed Thu Fri Sat Sun
        }
    }
}
