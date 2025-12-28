/**
 * A class is a blueprint for an object.
 * 
 * Instance variables (fields) is what an object knows.
 * Methods is what an object does.
 */

package com.minte9.basics.classes;

public class Blueprint {
    public static void main(String[] args) {
        
        MyClass obj = new MyClass();
        obj.setName("John");

        System.out.println("Hello " + obj.name);  // Hello John
    }
}

class MyClass {
    public String name;

    public void setName(String name) { // This is a class method
        this.name = name;
    }
}