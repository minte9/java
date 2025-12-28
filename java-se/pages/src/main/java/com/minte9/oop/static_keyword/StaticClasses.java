/**
 * Only nested classes can be static.
 * We can use the nested class without an instance for outer class.
 */

package com.minte9.oop.static_keyword;

public class StaticClasses {
    public static void main(String[] args) {
        Car.Manual.showInfo();  // 220
    }
}

class Car {
    static int maxSpeed = 220;  // general for this car model

    // static inner class
    static class Manual {  // Look Here
        public static void showInfo() {
            System.out.println(maxSpeed);
        }
    }
}