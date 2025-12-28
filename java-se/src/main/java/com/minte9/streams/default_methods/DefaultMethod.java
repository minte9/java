/**
 * One of the biggest changes in Java 8 is to collections library.
 * 
 * It required the introduction of a new concept, ...
 * default methods in interfaces.
 * 
 * A dfault methods allows body implementations.
 * Classes implementations win over default methods.
 */

package com.minte9.streams.default_methods;

public class DefaultMethod {
    public static void main(String[] args) {
        
        new A().hello(); // Hello default
        new B().hello(); // Hello B
    }
}

interface MyInterface {
    public default void hello() {
        System.out.println("Hello default");
    }
}

class A implements MyInterface { // Look Here
    // no implementation
}

class B implements MyInterface {
    @Override public void hello() {
        System.out.println("Hello B");
    }
}