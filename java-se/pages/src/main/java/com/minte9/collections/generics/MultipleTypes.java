/**
 * A generic class can have more than one type parameter.
 * Example: Box<T, U>, Map<K, V>, Entry<K, V>, etc.
 * 
 * Each type parameter must have a UNIQUE NAME.
 */

package com.minte9.collections.generics;

public class MultipleTypes {
    public static void main(String[] args) {
        
        // Box<T, U> can accept two different types
        Box<Integer, Integer> box = new Box<>();
        box.set(10);
        System.out.println(box.get()); // 10
    }

    // Generic class with one type parameters: T
    static class Box<T, U> {

        private T t;
        public void set(T t) { 
            this.t = t; 
        }
        public T get() { 
            return t; 
        }
    }

    // static class Word<T, T> {} // ❌ Error: Duplicate type parameter T
}