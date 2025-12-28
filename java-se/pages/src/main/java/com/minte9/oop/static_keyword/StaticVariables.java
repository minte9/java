/**
 * A static variables is initialized only once.
 * It is common to all objects created with that class
 * It can be called without object instantiation
 * 
 * Static variable usage: Students entering a classroom.
 * Each time a new student is created, we increase a shared counter.
 */

package com.minte9.oop.static_keyword;

public class StaticVariables {
    public static void main(String[] args) {
        ClassRoom cs = new ClassRoom();

        cs.newEntry(new Student("Mary"));
        System.out.println(ClassRoom.totalStudents);  // 1 (accessed in static way)

        cs.newEntry(new Student("John"));
        System.out.println(ClassRoom.totalStudents);  // 2
    }
}

class ClassRoom {
    public static int totalStudents = 0;  // Look Here

    public void newEntry(Student student) {
        totalStudents++;
    }
}

class Student {
    public Student(String name) {}
}