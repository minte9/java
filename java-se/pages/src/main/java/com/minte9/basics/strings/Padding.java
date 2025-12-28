/**
 * To pad a string use String.format()
 * Pad LEFT  %6s 
 * Pad RIGHT %-6s 
 */
package com.minte9.basics.strings;

public class Padding {
    public static void main(String[] args) {
        
        String a = "0" + String.format("%6s", "123"); // LEFT
        String b = String.format("%6s", "123").replace(" ", "0");
        String c = String.format("%-6s", "123").replace(" ", "0");

        System.out.println(a); // 0  123
        System.out.println(b); // 000123
        System.out.println(c); // 123000
    }
}