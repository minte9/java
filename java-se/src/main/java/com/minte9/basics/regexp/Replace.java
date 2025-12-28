/**
 * Matcher replaceAll()
 * 
 * Replace the matches from pattern with the provided string
 */

package com.minte9.basics.regexp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replace {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("abc.*? ");
        Matcher m = p.matcher("abc abcd bcd cef");
            // abc, char (zero or more), ungreedy, space

        while(m.find()) {
            String r = m.replaceAll("AAA "); // Look Here
            System.out.println(r); // AAA AAA bcd cef
        }
    }
}