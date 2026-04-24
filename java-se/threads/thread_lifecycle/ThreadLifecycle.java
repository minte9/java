/**
 * THREAD LIFECYCLE - DEMO
 * -----------------------
 * Demonstrates the main thread states during execution.
 * 
 * A Java thread goes through well-defined states during its lifetime.
 * NEW -> RUNNABLE -> (WAINTING / TIME_WAINTING/ BLOCKED) -> TERMINATED
 * 
 * COMMON MISCONCEPTIONS:
 * ----------------------
 *  ❌ “RUNNABLE means running”
 *      It means "ready to run"
 *   
 *  ❌ "Sleep release locks"
 *      sleep() does NOT release locks
 * 
 *  ❌ "Threads run in order"
 *      Scheduling is unpredictable
 */

package com.minte9.threads.thread_lifecycle;

public class ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);  // TIME_WAITING
            } catch (InterruptedException ex) {}
        });

        // NEW - thread created but not started
        System.out.println("State after creation: " + t.getState());

        t.start();
        // RUNNABLE - thread started
        System.out.println("State after start(): " + t.getState());

        Thread.sleep(500);
        // TIME WAITING - sleeping
        System.out.println("State after sleep(): " + t.getState());

        t.join();
        // TERMINATED - finished execution
        System.out.println("State after completion: " + t.getState());

        /**
            State after creation: NEW
            State after start(): RUNNABLE
            State after sleep(): TIMED_WAITING
            State after completion: TERMINATED
         */
    }
}
