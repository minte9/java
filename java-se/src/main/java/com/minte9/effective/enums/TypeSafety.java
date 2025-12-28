/**
 * Enum type safety ... 
 * 
 * The primary advantage of using enum vs constants is type safety.
 * With enum only the applicable values can be used. 
 */

package com.minte9.effective.enums;

public class TypeSafety {
    public static void main(String[] args) {

        Tools.print("This text is purple!", C.PURPLE);
            // This text is purple!

        Tools.print("This text is purple underline!", "\033[4;35m"); // allowed
            // This text is purple underline!

        Tools.output("This text is purple!", ColorEnum.PURPLE);
            // This text is purple!

        // Tools.output("This text is purple!", "\033[4;35m"); // Look Here
            // not applicable for the arguments
    }
}

class Tools {
    
    public static void print(String message, String color) {
        System.out.println(color + message + Color.RESET);
    }

    public static void output(String message, ColorEnum color) { // Look Here
        System.out.println(color + message + Color.RESET);
    }
}

class C {
    public static final String RESET  = "\u001B[0m";
    public static final String BLACK  = "\u001B[30m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";
    public static final String WHITE  = "\u001B[37m";
}

enum ColorEnum {

    RESET  { public String toString() { return "\u001B[0m";  } },
    BLACK  { public String toString() { return "\u001B[30m"; } },
    RED    { public String toString() { return "\u001B[31m"; } },
    GREEN  { public String toString() { return "\u001B[32m"; } },
    YELLOW { public String toString() { return "\u001B[33m"; } },
    BLUE   { public String toString() { return "\u001B[34m"; } },
    PURPLE { public String toString() { return "\u001B[35m"; } },
    CYAN   { public String toString() { return "\u001B[36m"; } },
    WHITE  { public String toString() { return "\u001B[37m"; } };
}