/**
 * Inheritance means that a subclass extends a super class.
 * Superclasses defines what subclasses have in common then abstract those features. 
 * 
 * Every class in Java extends class Object.
 * Object is the only class that has no superclass.
 */

package com.minte9.oop.inheritance;

public class Inheritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.setAction("barking");

        Bird b = new Bird();
        b.setAction("flying");

        d.doAction();  // The dog is barking
        b.doAction();  // The bird is flying
    }
}

abstract class Animal {
    public String action;
    public void setAction(String s) {
        action = s;
    }
    public abstract void doAction();
}

class Dog extends Animal {  // Look Here
    @Override
    public void doAction() {
        System.out.println("The dog is " + action);
    }
}

class Bird extends Animal {
    @Override
    public void doAction() {
        System.out.println("The bird is " + action);
    }
}