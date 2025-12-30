
/**
 * THREAD SLEEP vs JOIN
 * --------------------
 * They solve different problems.
 * 
 * NOTES:
 * ------
 * sleep() pauses the current thread.
 * join() waits for another thread to finish.
 * 
 * Both throw InterruptedException.
 * Prefere join() for correctness. 
 */

package com.minte9.threads.thread_lifecycle;

public class SleepVsJoin {
    public static void main(String[] args) 
            throws InterruptedException {
        
        Thread worker = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Worker done");
            } catch (InterruptedException ex) {}
        });

        worker.start();

        // ❌ This pauses main, but does NOT guarantee worker is done
        Thread.sleep(500);
        System.out.println("After sleep");

        // Main thread waits for worker to finish
        worker.join();
        System.out.println("After join");
    }
}
