/**
 * SCHEDULED EXECUTOR SERVICE
 * --------------------------
 * ScheduledExecutorService is the modern, correct replacement
 * for Timer, TimerTask, and most sleep() based loops.
 * 
 * WHAT PROBLEM DOES IT SOLVE:
 * ---------------------------
 *  - run a task after a delay
 *  - run a task perioadically
 *  - do this without manual threads or while(true) loops
 * 
 * IMPORTANT:
 * ----------
 * You schedule task, not threads.
 * Always shut down the scheduler.
 * 
 * NOTE:
 * -----
 * Do NOT use infinite while(true) loops + sleep.
 * With ScheduledExecutorService, periodic scheduling is infinite by default.
 * 
 * MENTAL MODEL:
 * -------------
 * ExecutorService run tasks once.
 * ScheduledExecutorService run tasks later or repeatedly.
 */

package com.minte9.threads.executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {
        
        ScheduledExecutorService scheduler = 
            Executors.newScheduledThreadPool(1);

        // Run task once after delay
        scheduler.schedule(() -> {
            System.out.println(
                "Task executed by " + Thread.currentThread().getName()
            );
        }, 3, TimeUnit.SECONDS);

        /*
            (wait 3 seconds)
            Task executed by pool-1-thread-1
        */

        // Run task repeatedly at a fixed rate
        scheduler.scheduleAtFixedRate(
            () -> System.out.println(
                "Heartbeat - " + Thread.currentThread().getName()
            ), 
            0,  // initial delay
            2,  // period 
            TimeUnit.SECONDS
        );

        sleep(8);
        scheduler.shutdown();

        /*
            Heartbeat - pool-1-thread-1
            Heartbeat - pool-1-thread-1
            Task executed by pool-1-thread-1
            Heartbeat - pool-1-thread-1
            Heartbeat - pool-1-thread-1
            Heartbeat - pool-1-thread-1
        */
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

