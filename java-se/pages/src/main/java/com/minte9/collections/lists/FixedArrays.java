/** 
 * The number of elements in an array is fixed and known.
 * The array allows fast index access.
 * 
 * You must provide array size: 
 *      myArray = new String[];  ❌ This won't work
 * 
 * Use enhanced `for` to iterate through an array.
 * Use `length` to get the size of an array. 
 * 
 * Arrays are faster, smaller in memory and used internally in many frameworks.
 * You will see array used in low-level parsing, high-performance code.
 */

package com.minte9.collections.lists;

public class FixedArrays {
    public static void main(String[] args) {

        // We know the students has EXACTLY 5 exam grades
        int[] grades = new int[5];

        grades[0] = 85;
        grades[1] = 90;
        grades[2] = 78;
        grades[3] = 92;
        grades[4] = 88;

        System.out.println("Exam grades: " + grades.length);  // Exam grades: 5
        for (int grade : grades) {
            System.out.print(grade + " ");  // 85 90 78 92 88
        }


        // Week days are fixed
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        System.out.println("Week days: ");
        for (String day : days) {
            System.out.print(day + " ");  // Mon Tue Wed Thu Fri Sat Sun 
        }
    }
}
