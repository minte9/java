/**
 * STRING FORMATING
 * ----------------
 * String.format() uses printf-style rules.
 * 
 * Formatting is for DISPLAY purposes - formatted values
 * are strings, not numbers.
 * 
 * Basic formatting example:
 *      %6s  -> right-align in a 6-character field (spaces on left)
 *      %-6s -> left-align in a 6-character field (spaces on right)
 */

package com.minte9.basics.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringFormat {
    public static void main(String[] args) {

        // Right align string, manually prefixed with a "0"
        String a = "0" + String.format("%6s", "123");

        // Right-align string, spaces replaces with zeros
        String b = String.format("%6s", "123").replace(" ", "0");

        // Left-align string, spaces replaces with zeros
        String c = String.format("%-6s", "123").replace(" ", "0");

        System.out.println(a); // 0  123
        System.out.println(b); // 000123
        System.out.println(c); // 123000

        // ---------------------------------------------------------
        
        List<Integer> N = new ArrayList<>();
        List<String> S = new ArrayList<>();
        
        N = Arrays.asList(100, 65, 50);
        S = Arrays.asList("java", "cpp", "python");

        for (int i=0; i<N.size(); i++) {
            System.out.print(

                // %-15s -> left align string, width 15
                String.format("%-15s", S.get(i)) +

                // %03d -> right align integer, width 3, padded with zeros
                String.format("%03d", N.get(i)) + 
                
                "\n"
            );
            // ------------------
            // java          100
            // cpp           065
            // python        050
        }
    }
}
