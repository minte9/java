/**
 * A method can throw multiple exceptions.
 * When you don't want to handle an exception, you can declare it.
 * The code inside finally block is always executed.
 */

package com.minte9.basics.exceptions;

public class Multiple_Exceptions {
    public static void main(String[] args) 
        throws NullPointerException { // Look Here

        try {
            test(-10); // Positive number required!
            test(Integer.valueOf(args[2])); // Index 2 out of bounds! 

        } catch (ArrayIndexOutOfBoundsException ex) { // Look Here
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Always executed!");
        }
    }
        
    public static void test(int n) 
        throws Exception, ArrayIndexOutOfBoundsException {
        
        if (n < 0) {
            throw new Exception("Positive number required");
        }
    }
}
