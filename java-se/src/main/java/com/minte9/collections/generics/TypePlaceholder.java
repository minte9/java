/**
 * Generics in Java
 * 
 * A generic type is a class or method that can operate on 
 * different data types while still providing TYPE SAFETY.
 * 
 * Without generics, everything is threated as Object.
 * This causes problems:
 * You can store anything (String, Integer, etc).
 * You must CAST whne retriving.
 * 
 * With generics (like MyClass<T>), Java enforces the type at 
 * COMPILE TIME.
 * 
 * Generic were introduces in Java 5 to eliminate those 
 * type-safe problems, especially when using collections.
 * 
 * What is T? 
 * 
 * T is a type placeholder.
 * When you write Box<String> T becomes String.
 * 
 * Non generic classes (A) is legal, but unsafe.
 * Java allowes it because generic where added later.
 */

package com.minte9.collections.generics;

public class TypePlaceholder {

    public static void main(String[] args) {
        
        // --- Non-generic class (unsafe) ----
        NonGenericClass A = new NonGenericClass();
        A.set(10);
        System.out.println(A.get()); // 10

        A.set("John");  // allowed (but unsage)
        System.out.println(A.get()); // John

        // This compile but is dangerous:
        //Integer x = (Integer) A.get(); // runtime crash

        // --- Generic class (safe) ---
        MyClass<Integer> B = new MyClass<>();
        B.set(10);
        System.out.println(B.get()); // 10

        // B.set("John"); // ❌ compile-time error
        // This is the whole point of generics.
    }
}

class NonGenericClass {
    private Object obj;
    public void set(Object o) { 
        obj = o;
    }
    public Object get() { 
        return obj; 
    }
}

// Generic class
class MyClass<T> {
    private T t;
    public void set(T t) { 
        this.t = t; 
    }
    public T get() { 
        return t; 
    }
}