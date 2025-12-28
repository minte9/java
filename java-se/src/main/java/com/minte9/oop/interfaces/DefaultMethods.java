/**
 * Java 8 introduced default methods in interfaces.
 * This allowed us to add implementations in interfaces.
 * Non default methods must be all defined as abstract (without body).
 */

package com.minte9.oop.interfaces;

public class DefaultMethods {
    public static void main(String[] args) {

        Parameter p = new Parameter();
        p.setValue(10);  // Value set in Parameter Class
        p.setType("Double");  // Type set in Parameter Class

        Character c = new Character();
        c.setValue(20);  // Value set in Character Class
    }
}

interface Customable {
    void setValue(int n);
    default void setType(Object o) {
        System.out.println("Type set in default method.");
    }
}

class Parameter implements Customable {
    @Override
    public void setValue(int n) {
        System.out.println("Value set in Parameter Class");
    }
    @Override
    public void setType(Object n) {
        System.out.println("Type set in Parameter Class");
    }
}

class Character implements Customable {
    @Override
    public void setValue(int n) {
        System.out.println("Value set in Character Class");
    }

    // No need to implement setType() unless we want custom behavior
}