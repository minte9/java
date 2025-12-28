/**
 * Composition
 * 
 * Give your new class a private field that ...
 * references an instance of the existing class.
 * 
 * The existing class becames a component to the new class.
 */

package com.minte9.effective.composition;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Composition {
    public static void main(String[] args) {

        S<String> s1 = new S<>(new HashSet<>());  
        S<String> s2 = new S<>(new TreeSet<>());  // Look Here

        s1.add("A"); // Call to S.add()
        s2.add("x"); // Call to S.add()

        s1.addAll(List.of("B", "C", "D")); // Call to S.addAll()
        s2.addAll(List.of("Y", "Z", "W")); // Call to S.addAll()

        assertEquals(4, s1.getCount()); // pass
        assertEquals(4, s2.getCount()); // pass
    }
}

class S<E> { // no inheritance

    private int count = 0;
    private final Set<E> set; // Look Here

    public S(Set<E> s) {
        set = s;
    }

    public boolean add(E e) {
        count += 1;
        System.out.println("Call to S.add()");
        return set.add(e); // Look Here
    }

    public boolean addAll(Collection<? extends E> c) {
        count += c.size();
        System.out.println("Call to S.addAll()");
        return set.addAll(c); // correct: call to super.add() - Look Here
    }

    public int getCount() {
        return count;
    }
}