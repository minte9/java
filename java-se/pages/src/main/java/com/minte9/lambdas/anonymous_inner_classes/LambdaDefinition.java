/**
 * WHAT ARE LAMBDAS?
 * -----------------
 * A lambda expression is a concise way to represent implementations
 * of functional interfaces.
 * 
 * A functional interface is an interface with one abstract method.
 * 
 * Insteed of creating an object with an anonymous inner class,
 * lambdas allow us to pass 'behavior' (executable code) directly.
 * 
 * COMMON LAMBDAS FORMS:
 * ---------------------
 * () -> expresion          // no parameters
 * () -> { statement }      // multiple statements
 * (x, y) -> x + y          // multiple parameters
 */

package com.minte9.lambdas.anonymous_inner_classes;

import java.util.function.BinaryOperator;

public class LambdaDefinition {
    public static void main(String[] args) {

        // -----------------------------------
        // Lambda with no arguments (Runnable)
        // -----------------------------------
        Runnable noArgs = () -> System.out.println("Hello World");
        new Thread(noArgs).run();
        
        // --------------------------------------
        // Lambda with a full block of statements
        // --------------------------------------
        Runnable multiStatement = () -> {
            System.out.println("Hello");
            System.out.println("World");
        };
        new Thread(multiStatement).run();

        // ---------------------------------------
        // Lambda with parameters (BinaryOperator)
        // ---------------------------------------
        BinaryOperator<Integer> op = (x, y) -> x + y;
        System.out.println(op.apply(2, 3));  // 5
    }
}
