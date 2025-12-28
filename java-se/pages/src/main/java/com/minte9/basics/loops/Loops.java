/** 
 * The 'for' is used when you know how many loops.
 * The 'enchanced-for' is used whne index is not needed.
 * The 'forEach' loops over a Collection, it is ofthen used with
 * lambdas or  method reference
 */

package com.minte9.basics.loops;
import java.util.Arrays;
import java.util.List;

public class Loops {            
    public static void main(String[] args) {
        
        System.out.print("\nFor loop: ");

        for (int i=0; i<3; i++) {
            System.out.print(i);
        }

        System.out.print("\nEnhanced for: ");

        int[] nums = {0, 1, 2};
        for (int no : nums) {
            System.out.print(no);
        }

        System.out.print("\nforEach with lambdas: ");

        List<Integer> A = Arrays.asList(0, 1, 2);
        A.forEach(x -> System.out.print(x));

        System.out.print("\nforEach with method reference: ");

        List<Integer> B = Arrays.asList(0, 1, 2);
        B.forEach(System.out::print);
    }
}

/**
    For loop: 012
    Enhanced for: 012
    forEach with lambdas: 012
    forEach with method reference: 012
 */