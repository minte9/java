/**
 * ScheduledExecutorService can run a task perioadically ...
 * or after an initial delay. 
 */

package com.minte9.threads.scheduler;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduled {    
    public static void main(String[] args) 
                        throws InterruptedException {

        // Create a service with 1 thread
        ScheduledExecutorService service = 
            Executors.newScheduledThreadPool(1);

        // Schedule a task to run repeatedly
        service.scheduleAtFixedRate(() -> {
            System.out.println(new Date() + " - scheduled");
        }, 5L, 10L, TimeUnit.SECONDS); // 5s, 10s delay

        // Main thread
        while(true) {
            System.out.println(new Date() + " - main");
            TimeUnit.SECONDS.sleep(10); // 10S
        }    
        
        /*
            Tue Oct 05 17:54:13 EEST 2021 - main
            Tue Oct 05 17:54:18 EEST 2021 - scheduled
            Tue Oct 05 17:54:23 EEST 2021 - main
            Tue Oct 05 17:54:28 EEST 2021 - scheduled
            ...
        */
    }
}