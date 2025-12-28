/**
 * Creating a thread is an expensive operation ...
 * and it should be minimized. 
 * 
 * Having worker threads minimizes the overhead ...
 * because executor service has to create the thread pool only once.
 * 
 * In a fixed thread-pool, the executor service makes sure ...
 * that the pool always has the specified number of threads running.
 */

package com.minte9.threads.scheduler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Executor {
    public static void main(String[] args) {
         
        ExecutorService service = Executors.newFixedThreadPool(2);
            // fixed pool = 2 thread at the same time
        
        Runnable task1 = () -> { // loop
            while(true) {
                System.out.println(
                    "Task 1 / " + Thread.currentThread().getName()
                );
                sleep(2);
            }
        };
        Runnable task2 = () -> { // only once
            System.out.println(
                "Task 2 / " + Thread.currentThread().getName()
            );
            sleep(1);
        };
        Runnable task3 = () -> { // loop
            while(true) {
                System.out.println(
                    "Task 3 / " + Thread.currentThread().getName()
                );
                sleep(1);
            }
        };

        service.submit(task1);
        service.submit(task2);
        service.submit(task3);
        service.shutdown();

        /*
            Task 1 / pool-1-thread-1
            Task 2 / pool-1-thread-2 - Look Here
            Task 1 / pool-1-thread-1
            Task 3 / pool-1-thread-2
            Task 1 / pool-1-thread-1
            Task 3 / pool-1-thread-2
            ...
        */
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ex) {}
    }
}