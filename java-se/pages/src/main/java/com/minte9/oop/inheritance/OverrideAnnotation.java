/**
 * Override annotation acts as a compile-time safeguard.
 * Abstract method with no Override will throw error only at compile time.
 */

package com.minte9.oop.inheritance;

public class OverrideAnnotation {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.settName();
    }
}

abstract class MyAbstractClass {
    abstract public void setName();
}

class MyClass extends MyAbstractClass {

    @Override 
    public void setName() {}  // Correct - with Override

    // ... Add another method later:

    // Wrong name and no Override annotation for safeguard.
    public void settName() {
        System.out.println("Wrong method called!");
    }
}
