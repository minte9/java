/**
 * ArrayList vs LinkedList
 * -----------------------
 * List is the interface. 
 * ArrayList and LinkedList are implementations
 * They store ordered collections and allow duplicates.
 * 
 * Key differences:
 * ---------------
 * ArrayList:
 *  - Backed by a dynamic array 
 *  - Fast access, O(1) for get()
 *  - Slower insert/remove in middle, O(n)
 * 
 * LinkedList:
 *  - Backed by a double linked list
 *  - Slower access, O(n)
 *  - Faster insert/remove, O(1) if the position is known
 * 
 * When to use:
 * ------------
 * Use ArrayList when:
 *  - You need fast random access
 *  - You mostly read data
 * 
 * Use LinkedList when:
 *  - You frequently add/remove elements
 *  - Especially at beggining or middle
 */
package com.minte9.collections.lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedList {
    public static void main(String[] args) {
        
        // Declaration
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        // Adding elements
        arrayList.add("A");
        arrayList.add("B");

        linkedList.add("A");
        linkedList.add("B");

        // Accesing elements
        System.out.println("ArrayList ge(1): " + arrayList.get(1));    // Fast
        System.out.println("LinkedList get(1): " + linkedList.get(1)); // Slower

        // Inserting in the middle
        arrayList.add(1, "X");  // Slower (shifts elements)
        linkedList.add(1, "X"); // Faster (node relinking)

        System.out.println("ArrayList: " + arrayList);   // [A, X, B]
        System.out.println("LinkedList: " + linkedList); // [A, X, B]

        // Removing elements
        arrayList.remove(2);  // Slower
        linkedList.remove(2); // Faster

        System.out.println("ArrayList: " + arrayList);   // [A, X]
        System.out.println("LinkedList: " + linkedList); // [A, X]
    }
}
