/**
 * Span strings on multiple lines
 * 
 * Concatenation operator +
 * String.join()
 * Text Block """ (only with JDK15)
 */

package com.minte9.basics.strings;

public class Multilines {
    public static void main(String[] args) {
        
        String a = ""
            + "AAA "
            + "BBB"
        ;
        String b = String.join("\n",
            "CCC",
            "DDD"
        );

        System.out.println(a); // AAA BBB
        System.out.println(b); // CCC DDD
    }
}
