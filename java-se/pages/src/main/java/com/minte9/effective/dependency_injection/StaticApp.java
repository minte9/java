/**
 * Using a static resource insteed of Dependency Injection.
 * 
 * This approach makes the false assumtion ...
 * that there is only one dictionary at a time.
 */

package com.minte9.effective.dependency_injection;
import java.util.Arrays;

public class StaticApp {
    public static void main(String[] args) {

        SpellCk EN = new SpellCk();
        EN.setDictionary(new String[] {"mother", "father"});

        SpellCk DE = new SpellCk();
        DE.setDictionary(new String[] {"mutter", "vater"});

        System.out.println(EN.isValid("mother")); // false - Error !
        System.out.println(DE.isValid("vater")); // true
    }
}

class SpellCk {
    private static String[] dictionary; // Look Here

    public Boolean isValid(String word) {
        return Arrays.asList(dictionary).contains(word);
    }

    public void setDictionary(String[] d) {
        dictionary = d;
    }
}