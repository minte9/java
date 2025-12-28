/**
 * CUSTOM FUNCTIONAL INTERFACE
 * ---------------------------
 * You can define your own functional interface when:
 *   - built-in ones (Predicate, Function, etc) don't fit
 *   - you want clearer domain-specific meaning
 * 
 * A functional interface may containt:
 *   - exactly ONE abstract method
 *   - zero or more default methods
 *   - zero or more static methods
 * 
 * Only the abstract method counts toward the "functional" rule.
 * 
 * A method reference works when the referenced method
 * has the SAME signature as the functional interface method.
 */

package com.minte9.lambdas.functional_interfaces;

public class CustomFunctionalInterface {
    public static void main(String[] args) {

        MyFuncInterface exclaim = s -> s + "!";
        MyFuncInterface question = s -> s + "?";

        System.out.println(exclaim.run("Hello World"));  // Hello World!
        System.out.println(question.run("Java Lambdas"));  // Java Lambdas?

        System.out.println(exclaim.describe());  // MyFuncInterface: transforms a String
    }
}

// Custom functional interface
@FunctionalInterface
abstract interface MyFuncInterface {

    // Single abstract method (SAM)
    String run(String s);

    // Default method (does NOT count as abstract)
    default String describe() {
        return "MyFuncInterface: transforms a String";
    }


}