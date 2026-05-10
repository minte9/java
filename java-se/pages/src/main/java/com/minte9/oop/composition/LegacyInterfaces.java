/**
 * Adding a new method to the interface can break other classes.
 * Extending an interface is also not recommended.
 * Where is possible, it is better to createa a new interface.
 */

package com.minte9.oop.interfaces;

public class LegacyInterfaces {
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        obj.setValue(10);  // Value set in ClassA
        obj.setType("Double");  // Type set in ClassA
    }
}

interface Variable {
    public void setValue(int n);
    public void setType(Object o); // NEW METHOD
}

class ClassA implements Variable {
    @Override
    public void setValue(int n) {
        System.out.println("Value set in ClassA");
    }
    @Override
    public void setType(Object n) {
        System.out.println("Type set in ClassA");
    }
}

/**
 * class ClassB implements Variable {
 *      @Override
 *      public void setValue(int n) {
 *        System.out.println("Value set / ClassB");
 *      }
 *  }
*/