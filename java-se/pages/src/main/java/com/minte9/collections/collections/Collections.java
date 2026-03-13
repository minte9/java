/**
 * COLLECTIONS - JCF
 * -----------------
 * In Java, Collections are a full arhitecture of interfaces, classes, utilities.
 * They are part of the Java Collection Framework (JCF), located in java.util package.
 * 
 * The JCF provides:
 *  - Interfaces (List, Set, Map, Queue)
 *  - Implementations (ArrayList, HashSet, HashMap, LinkedList)
 *  - Algorithms (sorting, searching)
 * 
 * COLLECTION INTERFACE
 * --------------------
 * Represents a group of objects (like a bag).
 * 
 * Main subinterfaces:
 *  - List (ordered, indexed access): ArrayList, LinkedList
 *  - Set (no duplicates): HashSet (unordered), TreeSet (ordered)
 *  - Queue/Deque (FIFO/LIFO): LinkedList, ArrayDeque
 * 
 * MAP INTERFACE
 * -------------
 * Map is NOT a subtype of Collection.
 * 
 * Stores key-value pairs.
 *  - HashMap, LinkedHashMap, TreeMap
 * 
 * ---------------------------------------
 * 
 *               Iterable
 *                   |
 *               Collection
 *       ┌───────────┼──────────┐
 *       |           |          |
 *      List        Set       Queue
 *       |           |          |
 *    ArrayList   HashSet    ArrayDeque
 *    LinkedList  TreeSet    PriorityQueue
 *
 *                 Map
 *                  |
 *               HashMap
 *               TreeMap
 *
 * 
 * COLLECTIONS vs ARRAYS
 * ---------------------
 * 
 * Arrays:
 *   - fixed size
 *   - can store primitives
 *   - no built-in utilities
 *   - hard to manipulate
 * 
 * Collections:
 *   - dynamic size
 *   - store objects (but wrappers allow primitives)
 *   - rich functionality
 *   - easy add/remove/find
 */

package com.minte9.collections.collections;

import java.util.*;

public class Collections {
    public static void main(String[] args) {

        // LIST (ordered, allows duplicates)
        // ---------------------------------
        List<String> names = new ArrayList<>();
        names.add("Alex");
        names.add("Maria");
        for (String n : names) {
            System.out.println(n); // Alex, Maria
        }

        // SET (duplicates, unordered)
        // ---------------------------
        Set<String> cities = new HashSet<>();
        cities.add("Bucharest");
        cities.add("Cluj");
        cities.add("Bucharest"); // duplicate ignored
        for (String c : cities) {
            System.out.println(c);  // Bucharest, Cluj
        }

        // MAP (key-value pairs)
        // ---------------------
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ana", 25);
        ages.put("Bogdan", 32);
        ages.put("Ana", 26);  // overwrites previous value
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(
                entry.getKey() + " -> " + entry.getValue()  // Ana -> 26  Bogdan -> 32
            );
        }
    }
}
