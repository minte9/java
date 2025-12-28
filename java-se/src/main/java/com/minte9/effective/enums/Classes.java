/**
 * Enum types
 * 
 * Are classes that exports one instance for each enumaration constant.
 * An Enum can start as a simple collection of enum and evolve over time.
 */

package com.minte9.effective.enums;

public class Classes {
    public static void main(String[] args) {

        System.out.println(Planet.MERCURY); 
            // MERCURY

        System.out.println(Color.PURPLE + "Purple 1!" + Color.RESET); 
            // Purple 1!

        System.out.println(Color2.PURPLE + "Purple 2!" + Color.RESET); 
            // Purple 2!
        
        System.out.println(Color3.PURPLE + "Purple 3!" + Color.RESET); 
            // Purple 3!
    }
}


enum Planet {  
    MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE 
}

enum Color {
    RESET, BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE;

    public String toString() { 
        switch(this) {
            case RESET:  return "\u001B[0m";
            case BLACK:  return "\u001B[30m";
            case RED:    return "\u001B[31m"; 
            case GREEN:  return "\u001B[32m";  
            case YELLOW: return "\u001B[33m"; 
            case BLUE:   return "\u001B[34m";
            case PURPLE: return "\u001B[35m"; 
            case CYAN:   return "\u001B[36m";  
            case WHITE:  return "\u001B[37m";
            default:     return null;
        }
    }
}

enum Color2 {
    RESET("\u001B[0m"),   BLACK("\u001B[30m"),  RED("\u001B[31m"), 
    GREEN("\u001B[32m"),  YELLOW("\u001B[33m"), BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"), CYAN("\u001B[36m"),   WHITE("\u001B[37m");

    private final String value;
    private Color2(String c) { 
        value = c;  
    }
    public String toString() { 
        return value; 
    }
}

enum Color3 {
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