/**
 * Constructor use the class name and has no return type.
 * Every class you create has a default constructor, even if you don't write one.
 * You could have more than one constructor in a class.
 */

package com.minte9.oop.constructors;

public class MultipleConstructors {
    public static void main(String[] args) {

        new A();  // Default constructor used
        new A("aaa");  // Constructor 1 - aaa
        new A(100);  // Constructor 2 - 100
    }
}

class A {

    // Default contructor (written explicitly for clarity)
    public A() {
        System.out.println("Default constructor used");
    }

    // Constructor 1
    public A(String s) {
        System.out.println("Constructor 1 - " + s);
    }

    // Constructor 2
    public A(int n) {
        System.out.println("Constructor 2 - " + n);
    }
}