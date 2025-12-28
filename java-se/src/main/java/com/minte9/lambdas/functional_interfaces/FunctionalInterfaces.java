/**
 * FUNCTIONAL INTERFACES
 * ---------------------
 * A functional interface is an interface with one abstract method.
 * 
 * Example:
 *  - Runnable
 *  - Comparator<T>
 *  - Predicate<T>
 *  - BinaryOperator<T>
 * 
 * @FunctionalInterface
 * public interface Predicate<T> {
 *      boolean test(T var1); // abstract
 *      ...
 * 
 * Functional interfaces are commonly used with lambdas, streams, 
 * and method references. 
 */

package com.minte9.lambdas.functional_interfaces;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaces {
    public static void main(String[] args) {

        // -----------------------------------------
        // Predicate using a lambda expression
        // -----------------------------------------
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(36));  // true

        // -------------------------
        // Function example
        // ----------------------------
        Function<Long, Long> increment = x -> x + 1;
        System.out.println(increment.apply(20L));  // 21
    }
}