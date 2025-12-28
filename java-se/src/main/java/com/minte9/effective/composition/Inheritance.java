/**
 * Inheritance
 * 
 * Superclass overriding methods could lead to undesired results.
 */

package com.minte9.effective.composition;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Inheritance {
    public static void main(String[] args) {

        HS<String> hs = new HS<>();  
        hs.add("A");
            //Call to HS.add()

        hs.addAll(
            Arrays.asList(new String[] {"B", "C", "D"})
        );
            // Call to HS.addAll()
            // Call to HS.add() +1
            // Call to HS.add() +1
            // Call to HS.add() +1

        assertEquals(4, hs.getCount()); 
            // fails, expected:<4> but was:<7>
    }
}

class HS<E> extends HashSet<E> {
    private int count = 0;

    @Override public boolean add(E e) {
        count += 1;
        System.out.println("Call to HS.add()");
        return super.add(e); // Look Here
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        count += c.size();
        System.out.println("Call to HS.addAll()");
        return super.addAll(c); // incorrect: call to HS.add() - Look Here 
    }

    public int getCount() {
        return count;
    }
}