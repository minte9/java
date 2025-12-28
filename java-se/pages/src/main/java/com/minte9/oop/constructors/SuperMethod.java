/**
 * The super() method is used to call parent constructor.
 * Constructor call must be the first statement in a controller.
 */

package com.minte9.oop.constructors;

public class SuperMethod {
    public static void main(String[] args) {
        new MainClass();  // Parent constructor called.
    }
}

class BaseClass {
    public BaseClass() {
        System.out.println("Parent constructor called.");
    }
}

class MainClass extends BaseClass {
    public MainClass() {   // Subclass controller
        super();  // Parent constructor called
    }

    /**
     * This constructor will not work because 'void' is the first statement.
     * public void MainClass(String s) {
     *      super(s);
     * }
     */
}


