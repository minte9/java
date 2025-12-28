/**
 * Before Enum, a commont pattern was to use group contants
 */

package com.minte9.effective.enums;

public class Constants {
    public static void main(String[] args) {

        System.out.println(
            Utils.Color.ANSI_RED + "This text is red!" + 
            Utils.Color.ANSI_RESET
        );
    }
} 

class Utils {

    public static class Color {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
    }
}