/**
 * THREAD STOP - COOPERATIVE CANCELLATION
 * ------------------------------------------
 * You might need to stop a thread on runtime.
 * 
 * Threads stop by cooperating, not by force.
 * A shared flag is used to signal termination.
 * 
 * RULES:
 * -----
 *  1) Threads should stop themselves - never be stopped externally.
 *  2) Shared state between threads must be visible (volatile, synchronization).
 *  3) Timing (sleep) is NOT synchronization.
 */

package com.minte9.threads.thread_lifecycle;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start main thread");

        Worker t1 = new Worker("A");
        Worker t2 = new Worker("B");
        
        new Thread(t1).start();
        new Thread(t2).start();

        Thread.sleep(2000); 

        t1.requestStop();
        t2.requestStop();

        Thread.sleep(1000); 
        System.out.println("Exit main thread");

        /*
            Start main thread
            Thread A / Run
            Thread B / Run
            Thread A / Run
            Thread B / Run
            Thread A / Exit
            Thread B / Exit
            Exit main thread
        */
    }
}

class Worker implements Runnable {

    private String name;

    // volatile guarantees visibility between threads
    private volatile boolean exit = false;

    public Worker(String name) { 
        this.name = name; 
    }

    @Override 
    public void run() {

        while(!exit) {
            System.out.println("Thread " + name + " / Run");
            sleep(1000);
        }
        
        System.out.println("Thread " + name + " / Exit");
    }

    public void sleep(int ms) {
        try { 
            Thread.sleep(ms); 
        } catch (InterruptedException e) {
            // ignored by simplicity
        }
    }

    public void requestStop() {
        exit = true;
    }
}