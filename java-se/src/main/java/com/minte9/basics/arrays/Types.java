/**
 * An array is forever an array
 * 
 * Creating an array of Dogs, not a new Dob object
 */
package com.minte9.basics.arrays;

public class Types {
    public static void main(String[] args) {
    
        Dog[] dogs = new Dog[4]; // array of Dogs
        dogs[0] = new Dog();
        dogs[1] = new Dog();
     // dogs[2] = 222; // Compiler Error 
        
        System.out.println(dogs.length); // 4
    }
}

class Dog {}