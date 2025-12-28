/**
 * A reference to an object can be overridden
 * 
 * After a overrites b, the b reference to object 2 is destroyed
 * Eligible for Garbage Collection
 */

package com.minte9.basics.objects;

public class References {
    
    public static void main(String[] args) {
        
        Book a = new Book("A");
        Book b = new Book("B");
        System.out.printf("%s%s /", a, b); // AB

        Book c = b; // refc, objB
        System.out.printf("%s%s%s /", a, b, c); // ABB

        b = a; // refb, objA, objB destroyed
        System.out.printf("%s%s%s /", a, b, c); // AAB
    }
}

class Book {
    String name;   

    public Book(String name) { // contructor
        this.name = name;
    }
    public String toString() {
        return name;
    }
}