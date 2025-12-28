/**
 * Telescoping constructors works, but ...
 * it is hard to write and hard to read.
 */

package com.minte9.effective.constructors;

public class Telescoping {

    public static void main(String[] args) {

        new Nutrition(240);
        new Nutrition(240, 100);
        new Nutrition(240, 100, 10);

        /*
            Constructor (size, calories, fat) 240 0 0
            Constructor (size, calories, fat) 240 100 0
            Constructor (size, calories, fat) 240 100 10
        */
    }
}

class Nutrition {

    public Nutrition(int size) { // one param always required
        this(size, 0);
    }

    public Nutrition(int size, int calories) {
        this(size, calories, 0);
    }

    public Nutrition(int size, int calories, int fat) {
        System.out.println(
            "Constructor (size, calories, fat) "
            + size + " " 
            + calories + " "
            + fat
        );
    }
}