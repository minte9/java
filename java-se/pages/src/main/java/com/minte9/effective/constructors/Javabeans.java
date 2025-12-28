/**
 * A JavaBeans constructor object ...
 * may be in an inconsistent state
 */

package com.minte9.effective.constructors;

public class Javabeans {
    public static void main(String[] args) {

        A a = new A();

        a.setSize(240);
            // setSize: 240

        a.setCalories(100);
            // setCalories: 100

        a.setFat(11);
            // setFat: 11
    }
}

class A {

    private int size = -1; // required, no default
    private int calories = 0;
    private int fat = 0;
    
    public void setSize(int s) { 
        size = s; 
        System.out.println("setSize: " + size);
    }

    public void setCalories(int s) { 
        calories = s; 
        System.out.println("setCalories: " + calories);
    }

    public void setFat(int s) { 
        fat = s; 
        System.out.println("setFat: " + fat);
    }
}