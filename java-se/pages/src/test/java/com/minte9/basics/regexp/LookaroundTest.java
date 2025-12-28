package com.minte9.basics.regexp;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class LookaroundTest {  

    public static boolean find(String regex, String str) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find();
    } 
    
    @Test public void lookBehind() {
        assertTrue(find("(?<=a)x", "axyz"));  // Look behind for a (true)
        assertFalse(find("(?<=b)x", "axyz")); // Look befind for a (false)
    }

    @Test public void lookAhead() {
        assertTrue(find("(?=x)xyz", "axyz")); // Look ahead for x (true)
        assertFalse(find("(?=x)ax", "axyz")); // Look ahead for x (false)
    } 

    @Test public void lookBehindNegative() {
        assertFalse(find("(!?<=a)x", "ax"));  // Look behind for !a (false)
    }

    @Test public void lookAheadNegative() {
        assertFalse(find("(?!x)x", "ax"));    // Look ahead for !x (false)
    }
}