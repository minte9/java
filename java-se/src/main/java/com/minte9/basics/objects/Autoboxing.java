/**
 * Java has a two-part type system, primitives (int, double, boolean, etc) and
 * reference types (String, List, etc).
 * 
 * Every primitive type has a corresponding reference type, called boxed primitive.
 * (Integer, Double, Boolean, etc).
 * 
 * Autoboxing reduces the verbosity, bot not the danger, of using boxed primitives.
 * Unboxing is the automatic conversion of objects into their coresponding primitives.
 */

package com.minte9.basics.objects;

import java.util.List;
import java.util.ArrayList;

public class Autoboxing {
    public static void main(String[] args) {
        
        List<Integer> li = new ArrayList<>();

        for (int i=10; i<20; i++) { 
            li.add(i);  // autoboxing (i is primitive)
        }
        System.out.println(li.get(0).getClass().getName());  // java.lang.Integer

        int b = li.get(0);   // unboxing (element is Integer)
        System.out.println(b);   // 10
    }
}
