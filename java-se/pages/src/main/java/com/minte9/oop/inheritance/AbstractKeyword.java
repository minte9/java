/**
 * By making the superclass abstract we prevent instantiation.
 * 
 * What exactly is a Pet object, what color, what size?
 * We don't know (the class must be declared as abstract).
 * 
 * An abstract class can be extended.
 * An abstract method must be overrided.
 */

package com.minte9.oop.inheritance;

public class AbstractKeyword {
    public static void main(String[] args) {
        MyDog myDog = new MyDog();
        myDog.setColor("gray");
        System.out.println(myDog.color);  // gray
    }    
}

abstract class Pet {
    protected String color;
    public abstract void setColor(String c);  // abstract method
    public void setName() {}  // non-abstract method (body)
}

class MyDog extends Pet {
    public MyDog() {}
    public void setColor(String c) {
        this.color = c;
    }
}
