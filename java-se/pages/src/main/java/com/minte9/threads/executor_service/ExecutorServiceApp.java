/**
 * EXECUTOR SERVICE - THREAD POOL
 * ------------------------------
 * 
 * ExecutorService allows us to run asynchron tasks 
 * without having to deal with the thread directly.
 * 
 * The executor service makes sure that the pool 
 * always has the specified number of threads running.
 * 
 * BENEFITS:
 * ---------
 *  - Avoids manual thread creation
 *  - Limits concurrency
 *  - Resuses threads efficientlty
 * 
 * TAKEAWAY: 🟡
 * ---------
 * An ExecutorSerice manages threads for you.
 * You submit tasks, not threads.
 * 
 */

package com.minte9.threads.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceApp {
    public static void main(String[] args) throws InterruptedException {

        // Fixed thread pool with 2 worker threads
        ExecutorService service = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> runTask("Task 1", 3);
        Runnable task2 = () -> runTask("Task 2", 2);
        Runnable task3 = () -> runTask("Task 3", 1);
        
        // Submit 3 tasks to a pool of size 2
        service.submit(task1);
        service.submit(task2);
        service.submit(task3);

        // No new tasks accepted
        service.shutdown();

        // Wait for all tasks to finish
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("All tasks finished");

        /*
            Possible output (order is NOT guaranteed):

            Task 2 started on pool-1-thread-2
            Task 1 started on pool-1-thread-1
            Task 2 finished on pool-1-thread-2
            Task 3 started on pool-1-thread-2
            Task 1 finished on pool-1-thread-1
            Task 3 finished on pool-1-thread-2
            All tasks finished
        */
    }

    private static void runTask(String name, int seconds) {
        System.out.println(
            name + " started on " + Thread.currentThread().getName()
        );

        sleep(seconds);

        System.out.println(
            name + " finished on " + Thread.currentThread().getName()
        );
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt
        }
    }
}
