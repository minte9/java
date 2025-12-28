/**
 * Threads are not separate processes on the OS user feels is like ...
 * separate processes.
 * 
 * A Java application starts up a main thread, main() on the bottom ...
 * of the stack.
 * 
 * A new thread creats a separate call stack, run() at the botttom ...
 * of the stack.
 * 
 * Threads can be created by using two mechanisms, extending Thread class ...
 * or implementing Runnable.
 */

package com.minte9.threads.create_thread;

public class Extending extends Thread { // Look Here
    public static void main(String[] args) {
  
        Thread t1 = new Extending();
        Thread t2 = new Extending();
        Thread t3 = new Extending();
        t1.start();
        t2.start();
        t3.start();

        /* 
            Thread-2 
            Thread-1
            Thread-0
            Thread-2
            Thread-0
            Thread-1
        */
    }

    public void run() {
        try {
            String name = Thread.currentThread().getName();

            System.out.println(name);
            Thread.sleep(1000);

            System.out.println(name);
            Thread.sleep(1000);

        } catch (InterruptedException ex) {}
    }
}