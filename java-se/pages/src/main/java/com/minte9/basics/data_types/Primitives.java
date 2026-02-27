/**
 * JAVA PRIMITIVES - INTEGER DATA TYPES
 * ------------------------------------
 * 
 * Java has 8 primitive data types:
 *  - char, boolean, byte, short, int, long, float, double
 * 
 * We focus on the primitives used to store whole numbers (integers):
 *  - byte : 8-bit signed integer
 *  - short: 16-bit signed integer
 *  - int  : 32-bit signed integer
 *  - long : 64-bit signed integer
 * 
 * These types differ mainly in how much memory they use
 * and the range of vaues they can store.
 * --------------------------------------
 */

package com.minte9.basics.data_types;

public class Primitives {
    public static void main(String[] args) {


        /**
         * ------------------------------------------------------
         * byte overflow
         * ------------------------------------------------------
         * Range: -128 to 127
         * Commonly used when memory is very limited
         */
        byte maxByte = 127;
        System.out.println("byte max value: " + maxByte);  // 127

        maxByte++;  // overflow
        System.out.println("after overflow: " + maxByte); // -128


        /**
         * ------------------------------------------------------
         * short overflow
         * ------------------------------------------------------
         * Size: 16 bits
         * Range: -32,768 to 32,767
         * 
         * Commonly used when memory is very limited.
         */
        short maxShort = 32_767;
        System.out.println("short max value: " + maxShort);

        maxShort++; // overflow
        System.out.println("after overflow: " + maxShort);  // -32768


        /**
         * ------------------------------------------------------
         * int overflow
         * ------------------------------------------------------
         * Size: 32 bits
         * Range: -2,147,483,648 to 2,147,483,647
         * 
         * Default and most commonly used integer type in Java.
         */
        int maxInt = Integer.MAX_VALUE;
        System.out.println("int max value: " + maxInt);

        maxInt++; // overflow
        System.out.println("after overflow: " + maxInt);  // -2147483648


        /**
         * ------------------------------------------------------
         * long overflow
         * ------------------------------------------------------
         * Size: 64 bits
         * Range: -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
         * 
         * Used when int is not large enough.
         * Long literals must end with L.
         */
        long maxLong = 9_000_000_000L;
        System.out.println("int max value: " + maxLong);

        maxLong++; // overflow
        System.out.println("after overflow: " + maxLong);  // 9000000001


        /**
         * ------------------------------------------------------
         * Preventing overflow
         * ------------------------------------------------------
         * Use Math.addExact() to throw an exception instead of
         * silently overflow.
         */
        try {
            int safeSum = Math.addExact(Integer.MAX_VALUE, 1);
            System.out.println("safe sum: " + safeSum);
        } catch (ArithmeticException e) {
            System.out.println("Overflow detected!");
        }
        // ---------------------------------------
        // Overflow detected!
        
    }
}
