/**
 * QUEUE BASICS - COLLECTIONS
 * --------------------------
 * Queue = Fist In, First Out
 * 
 * Example:
 * --------
 * Think of line at supermarket:
 *  - First persion in line, first served
 *  - New people join at the enc
 * 
 * Operations:
 * -----------
 *  - add() / offer - add to the end
 *  - remove() / poll() - remove from front
 *  - element / peek() - view front element
 * 
 * Java provides two versions of the same operations to handle failure differently:
 * --------------------------------------------------------------------------------
 *  - add() throw exception if fails
 *  - offer() return false if fails
 * 
 *  - remove() throws exception if empty
 *  - poll() return null if empty
 * 
 *  - element() throws exception if empty
 *  - peek() return null if empty
 * 
 * Best practice:
 * -------------
 * Prefer offer(), poll(), peek().
 * They are safer and more commonly used.
 */

package com.minte9.collections.queues;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueBasics {
    public static void main(String[] args) {
        
        // Create a Queue
        Queue<String> queue = new ArrayDeque<>();

        // Add elements (People entering line)
        queue.offer("Alice");
        queue.offer("Bob");
        queue.offer("Charlie");

        // View front (Next to be served)
        String first = queue.peek();
        System.out.println("Next customer: " + first);  // Alice

        // Remove element (Serving customer)
        String served = queue.poll();
        System.out.println("Served: " + served);  // Alice
        System.out.println("Queue after serving: " + queue);  // [Bob, Charlie]

        // Add more people
        queue.offer("Diana");
        queue.offer("Eva");
        System.out.println("Updated queue: " + queue);  // [Bob, Charlie, Diana, Eva]

        // Process entire queue
        while (!queue.isEmpty()) {
            System.out.println("Serving: " + queue.poll()); 
                // Bob
                // Charlie ...
        }
    }
}