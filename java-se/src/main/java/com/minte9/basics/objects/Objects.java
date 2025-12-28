/** 
 * To use an object you must declare a reference variable.
 * JVM allocates space for the object.
 * 
 * The reference variable is forever of that type.
 * You cannot assign an object to another type.
 * myDog = new Cat(); // ❌ Type mismatch error
 */

package com.minte9.basics.objects;

public class Objects {
    public static void main(String[] args) {
    
        Dog myDog = new Dog();
        myDog.size = 40;
        myDog.bark();
    }
}

class Dog {
    int size;
        
    void bark() {
        System.out.println("Ham Ham");  // Ham Ham
    }
}