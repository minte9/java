/**
 * STREAM - COMPARING
 * ------------------------------------------------------------
 * Comparing is used when you want to find minimum or maximum 
 * of an element. 
 */

package com.minte9.streams.common_operations;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamComparing {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
            new Student("John", 23),
            new Student("Mary", 30),
            new Student("Mike", 27)
        );

        Student expected = students.get(0);
        Student youngest = students
                    .stream()
                    .min(Comparator.comparing(x -> x.age()))
                    .get();

        assertEquals(expected, youngest); // pass

        System.out.println("Done");
    }
}

record Student(String name, int age) {}