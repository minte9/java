/**
 * Greedy behavior match the longest sequence.
 * Use "?" modifier for ungreedy behavior.
 */

package com.minte9.basics.regexp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Greedy {
    public static void main(String[] args) {

        Pattern p;
        Matcher m;

        String txt = "extend cup end table";

        // Greedy
        p = Pattern.compile("e.+d");
        m = p.matcher(txt);
        while(m.find()) {
            System.out.println(m.group()); // extend cup end
        }

        // Ungreedy
        p = Pattern.compile("e.+?d");
        m = p.matcher(txt);
        while(m.find()) {
            System.out.println(m.group()); // extend
                                           // end
        }


        // Group example
        p = Pattern.compile("Java ?(8|SE)");
        m = p.matcher("Java 8 Java SE");
        while(m.find()) {
            System.out.println(m.group()); // Java 8
                                           // Java SE
        }
    }
}