/**
 * Pattern, String split()
 * Break the string in parts using regex pattern 
 * Split by char
 * Split by camer case
 */

package com.minte9.basics.regexp;
import java.util.regex.Pattern;

public class Split {
    public static void main(String[] args) {

        String[] words;

        Pattern p = Pattern.compile("[ ,.!]");
        words = p.split("one two,alpha9 12!done.");
        for (String w:words) {
            System.out.println(w); // one two alpha9 12 done
        }

        words = "AbCdEf".split("(?=[A-Z])"); // Look Ahead
        for(String w:words) {
            System.out.println(w); // Ab Cd Ef
        }
    }
}