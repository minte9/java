/**
 * A class or method should have only one reason to change.
 * Also, this responsability should be encapsulated.
 * 
 * Counting prime numbers - no looping encapsuation!
 */

package com.minte9.streams.single_responsibility;

import static org.junit.Assert.assertEquals;

public class Encapsulation {
    public static void main(String[] args) {
        
        assertEquals(4, countPrimes(10)); // pass
    }
    
    public static long countPrimes(int limit) {

        int n = 0;
        for (int i=2; i<limit; i++) { // counting loop - no encapsulation!    
            if (isPrime(i)) n++;
        }
        return n;
    }

    private static boolean isPrime(int n) { // check

        for (int i=2; i<n; i++) {
            if (n%i == 0) return false;
        }
        return true;
    }
}