/**
 * With lambdas you can easily make methods that are inline with SRP ...
 * one responsability and encapsulation.
 * 
 * Later, we can speeed up the time by using more CPU resources ...
 * with parallel method, without changing any other code.
 */

package com.minte9.streams.single_responsibility;
import static org.junit.Assert.assertEquals;
import java.util.stream.IntStream;

public class Lambdas {
    public static void main(String[] args) {

        assertEquals(25, MyNumbers.countPrimes(100)); // pass

        System.out.println(
            MyNumbers.countBigPrimes(200000) // 17984
        );
    } 
}

class MyNumbers {

    public static long countPrimes(int limit) {
        return IntStream.range(2, limit)
            .filter(MyNumbers::isPrime)
            .count()
        ;
    }

    private static boolean isPrime(int number) {
        return IntStream.range(2, number)
            .allMatch(x -> number % x != 0)
        ;
    }

    public static long countBigPrimes(int limit) {
        return IntStream.range(2, limit)
            .parallel()                     // Look Here
            .filter(MyNumbers::isPrime)
            .count()
        ;
    }
}