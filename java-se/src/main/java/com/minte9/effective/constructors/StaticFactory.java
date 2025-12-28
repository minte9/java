/**
 * Static factory methods return an instance of a class.
 * 
 * One advantage is that, unlike constructors, they have names.
 * The resulting code is easier to read.
 * 
 * Second, you don't need to create an object every time.
 */

package com.minte9.effective.constructors;

public class StaticFactory {
    public static void main(String[] args) {

        MyClass.getInstance();
            // Class constructor

        MyClass.getInstance();
            // nothing
    }
}

class MyClass {

    private static final MyClass INSTANCE = new MyClass();

    public static MyClass getInstance() { // Look Here
        return INSTANCE; 
    }

    private MyClass() {
        System.out.println("Class constructor");
    }
}