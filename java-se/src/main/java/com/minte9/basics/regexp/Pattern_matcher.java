/**
 * The Pattern matches() returns true only when preciseley matches the pattern.
 * To check only parts of the string find() is used.
 */

package com.minte9.basics.regexp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pattern_matcher {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("Version");  // regex pattern
        Matcher m = p.matcher("Version 1.0");  // content

        Boolean match_exactly = m.matches();
        Boolean found_parts = m.find();

        System.out.println(match_exactly);  // false
        System.out.println(found_parts);   // true
    }
}