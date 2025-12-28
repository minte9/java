/**
 * Primes counting App ...
 * 
 * Multiple responsibilities: counting & checking
 * 
 * A prime number is a natural number greater than 1 ...
 * that is not a product of two smaller natural numbers. 
 */

package com.minte9.streams.single_responsibility;
import static org.junit.Assert.assertEquals;

public class Primes {
    public static void main(String[] args) {
        
        assertEquals(4, noOfPrimes(10)); // pass
    }

    public static long noOfPrimes(int limit) {

        int n = 0;
        for (int i=2; i<limit; i++) { // counting responsibility

            for (int j=2; j<i; j++) { // checking responsibility

                if (i%j == 0) { // is prime

                    n++; break;
                }
            }
        }
        return n;
    }
}