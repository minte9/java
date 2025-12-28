/**
 * With Dependency Injection ...
 * the class does not create the resource directly.
 */

package com.minte9.effective.dependency_injection;

import java.util.Arrays;
import java.util.Objects;

public class ResourceApp {

    public static void main(String[] args) {

        String[] en = new String[] {"mother", "father"};
        String[] de = new String[] {"mutter", "vater"};

        SpellChecker EN = new SpellChecker(en); // Look Here
        SpellChecker DE = new SpellChecker(de);

        System.out.println(EN.isValid("mother")); // true
        System.out.println(DE.isValid("vater")); // false
    }
}

class SpellChecker {
    private final String[] dictionary;

    public SpellChecker(String[] d) { // Look Here - DI

        dictionary = Objects.requireNonNull(d);
    }

    public Boolean isValid(String word) {
        return Arrays.asList(dictionary).contains(word);
    }
}