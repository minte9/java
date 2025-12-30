/**
 * THREADS - EXTENDING Thread
 * --------------------------
 * 
 * WHAT IS A THREAD?
 * -----------------
 *  - A thread is a lightweight unit of execution inside a process.
 *  - Threads share the same memory (heap), but each thread has:
 *      - its own call stack
 *      - its own execution path
 * 
 * THREADS vs PROCESSES:
 * ---------------------
 *  - Processes are isolated (separate memory space)
 *  - Threads run inside the same process and share memory
 *  - From a user's perspective, threads may feel like separate programs
 * 
 * JAVA THREAD MODEL:
 * ------------------
 *  - Every Java application starts with a single thread: the "main" thread
 *  - The main thread starts execution at main()
 *  - When a new thread is started:
 *      - a new call stack is created
 *      - execution begin at run()
 * 
 * WAYS TO CREATE A THREAD:
 * ------------------------
 * 1) Extend the Thread class (this example)
 * 2) Implement the Runnable interface
 * 
 * NOTES:
 * -----
 *  - Calling start() creates a new thread
 *  - Calling run() directly does not create a thread
 */

package com.minte9.threads.create_thread;

public class ExtendingThread extends Thread { // Thread subclass
    public static void main(String[] args) {
        
        // Creating multiple thread objects
        Thread t1 = new ExtendingThread();
        Thread t2 = new ExtendingThread();
        Thread t3 = new ExtendingThread();

        // Starting threads (each creates a new call stack)
        t1.start();
        t2.start();
        t3.start();
    
        /*
            Possible output (order is NOT guaranteed):

            Thread-2
            Thread-1
            Thread-0
            Thread-2
            Thread-0
            Thread-1
        */
    }

    @Override
    public void run() {
        
        try {
            String name = Thread.currentThread().getName();

            System.out.println(name);
            Thread.sleep(1000);  // pause current thread

            System.out.println(name);
            Thread.sleep(1000);

        } catch (InterruptedException ex) {}
    }
}
