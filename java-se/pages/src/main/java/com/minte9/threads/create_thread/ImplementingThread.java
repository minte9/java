/**
 * THREADS - IMPLEMENTING Runnable
 * -------------------------------
 * This is the PREFERED way to create threads in Java. 
 * 
 * WHY Runnable IS BETTER THAN EXTENDING Thread:
 * --------------------------------------------
 *  - Java supports single inheritance (you can extend only one class)
 *  - Runnable separates:
 *      WHAT should run - Runnable (task)
 *      HOW it runs     - Thread (execution)
 *  - Allows better design and reuse
 * 
 * Runnable is a FUNCTIONAL INTERFACE:
 * -----------------------------------
 *  - It has a single abstract method: run()
 *  - This makes it compatible with:
 *      - anonymous classes
 *      - lambda expressions (Java 8+)
 * 
 * THREAD EXECUTION FLOW:
 * ----------------------
 *  - main() runs on the "main" thread
 *  - Each call to start():
 *      - creates a new call stack
 *      - executes run() in a new thread
 * 
 * NOTE:
 * -----
 * Calling start() creates a new thread.
 * Calling run() directly does NOT.
 * 
 * new Thread(r).run();   // ❌ runs in MAIN thread
 * new Thread(r).start(); // ✅ runs in NEW thread
 * 
 * This mistake causes many bugs.
 * 
 * KEY CONCEPTS:
 * -------------
 * Runnable represents a task, not a thread.
 * Lambdas make Runnable concise.
 * Execution order is unpredictable.
 */

package com.minte9.threads.create_thread;

public class ImplementingThread {
    public static void main(String[] args) {
        
        // Executed by the main thread
        System.out.println(Thread.currentThread().getName());

        // ----------------------------------
        // 1) Runnable implemented by a class
        // ----------------------------------
        new Thread(new MyClass2()).start(); // Thread-0

        // ----------------------------------
        // 2) Anonymous Runnable class
        // ----------------------------------
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();  // Thread-1

        // ----------------------------------
        // 3) Lambda expression (Runnable)
        // ----------------------------------
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start(); // Thread-2

        // Still running on the main thread
        System.out.println("Back in Main");

        /*
            Possible output (order is NOT guaranteed):

            main
            Thread-0
            Thread-1
            Back in Main
            Thread-2
        */
    }
}

/**
 * A concreate Runnable implementation.
 * Represents a UNIT OF WORK, not a thread.
 */
class MyClass2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
