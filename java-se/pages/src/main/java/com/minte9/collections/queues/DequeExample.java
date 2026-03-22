/**
 * DEQUE - COLLECTIONS
 * -------------------
 * It allows insertion and removal from BOTH ends:
 * This makes it more powerful than a Queue.
 * 
 * Queue:
 *  - FIFO (First In, First Out)
 *  - Add: at end
 *  - Remove: from front
 * 
 * Deque:
 *  - FIFO (like Queue)
 *  - LIFO (like Stack)
 *  - Add/remove from BOTH ends
 * 
 * Advantages over Queue:
 * ----------------------
 * More flexible:
 *  - addFirst(), addLat()
 *  - removeFirst(), removeLast()
 * 
 * Better performance with ArrayDeque.
 * 
 * Best practice:
 *  - Use Deque for most cases
 */

package com.minte9.collections.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {
        
        // Create Deque
        Deque<String> deque = new ArrayDeque<>(); // Both ends (not like Queue)

        // Use as queue (FIFO)
        deque.offerLast("Alice");  // same as addLast (but better)
        deque.offerLast("Bob");
        deque.offerLast("Charlie");
        System.out.println("Serve: " + deque.pollFirst()); // remove from front (Alice)

        // Use as stack (LIFO)
        deque.push("X");  // same as addFirst
        deque.push("Y");
        deque.push("Z");
        System.out.println("Pop: " + deque.pop());  // Z

        // Working with both end
        deque.addFirst("Start");
        deque.addLast("End");
        System.out.println(deque);  // [Start, Y, X, Bob, Charlie, End]
        System.out.println("Remove first: " + deque.removeFirst());  // Start
        System.out.println("Remove last: " + deque.removeLast());  // End

        // Peek operations
        System.out.println("Peek first: " + deque.peekFirst());  // Y
        System.out.println("Peek last: " + deque.peekLast());  // Charlie

    }
}
