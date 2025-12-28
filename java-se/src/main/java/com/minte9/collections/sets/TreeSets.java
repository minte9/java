/**
 * TreeSet stores UNIQUE elements and automatically keeps them sorted.
 * 
 * Operations like add(), remove(), contains() run in O(log n),
 * which is slightly slower than HashSet (O(1) average)
 * but still very efficient.
 */

package com.minte9.collections.sets;

import java.util.TreeSet;
import java.util.Set;

public class TreeSets {
    public static void main(String[] args) {

        Set<A> myTree = new TreeSet<>();
        myTree.add(new A("F", "1"));
        myTree.add(new A("G", "2"));
        myTree.add(new A("H", "4"));
        myTree.add(new A("H", "3"));

        System.out.println(myTree); // [F:1, G:2, H:4]
    }
}

class A implements Comparable<A> {

    public String title;
    public String artist;

    public A(String t, String a) {
        title = t;
        artist = a;
    }

    @Override
    public int compareTo(A a) {
        return title.compareTo(a.title);
    }

    @Override
    public String toString() {
        return title + ":" + artist;
    }
}