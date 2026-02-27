/**
 * JAVA CLASSES
 * --------------------------------------
 * In Java, everything goes into a class.
 *      - src/main/Classes.java
 * 
 * Java virtual machine (JVM) compiles the class and creates an archive.
 *      - target/classes/Class.class
 * 
 * Key Points:
 *  - Every java program starts in main() method.
 *  - Return type `void` means there is no returned value.
 *  - Every statement must end in semicolon.
 * 
 * Objects:
 *  - A class is a blueprint for an object.
 *  - Instance variables (fields) is what an object knows.
 *  - Methods are what an object does.
 * -----------------------------------
 */

package com.minte9.basics.classes;

public class Classes {
    public static void main(String[] args) {
        
        A obj = new A();
        obj.setName("John");

        System.out.println("Hello " + obj.name);  // Hello John
    }
}

class A {
    public String name;

    public void setName(String name) { // This is a class method
        this.name = name;
    }
}