/**
 * Stream Comparator ...

 * Comparing to find minimum or maximum of an element (example)
 */

package com.minte9.streams.common_operations;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Comparing {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
            new Student("John", 23),
            new Student("Mary", 30),
            new Student("Mike", 27)
        );

        Student youngest = students.stream()
            .min(Comparator.comparing(x -> x.getAge()))
            .get();
        assertEquals(students.get(0), youngest); // pass
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}