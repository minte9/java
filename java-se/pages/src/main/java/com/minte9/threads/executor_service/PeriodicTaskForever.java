/**
 * PERIODIC TASK - RUN FOREVER
 * ---------------------------
 * This simulates a background service.
*/

package com.minte9.threads.executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PeriodicTaskForever {
    public static void main(String[] args) {
        
        ScheduledExecutorService scheduler = 
            Executors.newSingleThreadScheduledExecutor();
        
        scheduler.scheduleAtFixedRate(
            new Worker(), 
            0,  // initial delay
            5,  // period
            TimeUnit.SECONDS
        );

        // No shutdown()
        // Application keeps running

        /*
            Heartbeat from pool-1-thread-1
            Heartbeat from pool-1-thread-1
            Heartbeat from pool-1-thread-1
            ...
        */
    }
}

class Worker implements Runnable {
    @Override
    public void run() {
        System.out.println(
            "Heartbeat from " +
            Thread.currentThread().getName()
        );
    }
}
