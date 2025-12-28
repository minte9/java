/**
 * Variables in Java are passed by value (copy), not by reference
 * 
 * In this example, first variable x = 7 bits are copied (00000111).
 * Then this copy goes in z variable (z = x)
 * Then after variable z changes (z = 0), x is not changed.
 */

package com.minte9.basics.variables;

public class PassedByValue {
    public static void main(String[] args) {

        int x = 7; // 00000111
        int z = x; // x bits are copied in z

        System.out.println("First: x == z " + (x == z));

        z = 0;  // x is not changed
        
        System.out.println("Second: x != z " + (x != z));

        
    }
}

/**
    First: x == z true
    Second: x != z true
*/