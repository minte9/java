/**
 * Generic type cannot be used in STATIC context.
 * 
 * Example:
 *      static T value;   // ❌ compiler error
 * 
 * Static fields belong to the CLASS, not to individual objects.
 * Generic type (T) only exists per OBJECT INSTANCE.
 * 
 * Generics cannot use PRIMITIVES.
 * 
 * Example:
 *      Box<int> box;    // ❌ not allowed
 * 
 * Java uses TYPE ERASURE.
 * After compilation, generics become Objects.
 * Primitives (int, double, etc.) are not objects.
 * 
 * Use their wrapper classes insteed:
 *      Box<Integer> box = new Box<>();
 */

package com.minte9.collections.generics;

public class GenericsLimitations {
    public static void main(String[] args) {
        
        Box<Integer> b1 = new Box<>();
        Box<String> b2 = new Box<>();

        System.out.println(b1.hashCode());  // 1878246837
        System.out.println(b2.hashCode());  // 929338653
        
        // Box<int> box = new Box<int>();  // ❌ primitives not allowed
    }

    static class Box<T> {
        
        //static T t;  // ❌ ERROR: static reference to non-static type T

        private T t;
        public void set(T t) { 
            this.t = t; 
        }
        public T get() { 
            return t; 
        }
    }
}