/**
 * METHOD REFERENCES (::)
 * ----------------------
 * Method references are a compact syntax for lambdas that
 * simply call an existing method.
 * 
 * Insteed of writting:
 *      x -> someMethod(x)
 * 
 * We can write:
 *      ClassName::someMethod
 * 
 * IMPORTANT:
 * ----------
 *  - Method refrences do NOT call the method
 *  - They only reference it
 *  - The method is executed by the functionl interface
 * 
 * Method references work only when:
 *  - The method signature matches the funtional interface
 * 
 * WHY System.out::println WORKS?
 * ------------------------------
 * forEach() espects a Consumer<String> which has:
 *      void accept(String s);
 * 
 * println() has the same shape:
 *      void println(String s);
 * 
 * So Java can safely connect them.
 */

package com.minte9.lambdas.method_references;

import java.util.Arrays;
import java.util.List;

public class MethodReferences {
    public static void main(String[] args) {
        
        List<String> names = Arrays.asList("Ana", "Bob", "John");

        // ---------------------------------------
        // Lambda version
        // ---------------------------------------
        names.forEach(n -> System.out.println(n));

        // ---------------------------------------
        // Method reference version
        // ---------------------------------------
        names.forEach(System.out::println);
    }
}
