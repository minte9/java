/**
 * When a class implements an interface it agrees to a contract.
 * This is forcing the developer to implement interface's methods.
 * 
 * All methods in an interface must be declared abstract.
 */

package com.minte9.oop.interfaces;

public class Interfaces {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.move();  // The cat is moving
        cat.play();  // The cat is playing
    }
}

abstract class Feline {
    public abstract void move();
}

interface Playable {
    public abstract void play();
}

class Cat extends Feline implements Playable {  // Look Here
    public void move() {
        System.out.println("The cat is moving");
    }
    public void play() {
        System.out.println("The cat is playing");
    }
}
