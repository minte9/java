/**
 * MULTILINES STRINGS
 * ------------------
 * Java (before JDK15) does not permit strings to span lines.
 * 
 * We can span strings on multiple lines using:
 *      - Concatenation operator +
 *      - String.join()
 *      - Text Block """ (only with JDK15)
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
