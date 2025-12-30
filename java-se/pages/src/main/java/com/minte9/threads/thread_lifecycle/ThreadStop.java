/**
 * A thread is automatically destroyed when the run() method has completed.
 * 
 * You might need to stop a thread on runtime.
 * Use a flag to control thread run() execution.
 */

package com.minte9.threads.sleep;

public class Stop {
    public static void main(String[] args) 
                        throws InterruptedException {

        System.out.println("Start main thread");

        ThreadClass t1 = new ThreadClass("A");
        ThreadClass t2 = new ThreadClass("B");
        
        new Thread(t1).start();
        new Thread(t2).start();
        Thread.sleep(2000); 

        t1.stop();
        t2.stop();

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

class ThreadClass implements Runnable {

    private String name;
    private Boolean exit = false;

    public ThreadClass(String n) { 
        name = n; 
    }

    @Override public void run() {

        while(!exit) {
            System.out.println("Thread " + name + " / Run");
            sleep(1000);
        }
        
        System.out.println("Thread " + name + " / Exit");
    }

    public void sleep(int ms) {
        try { 
            Thread.sleep(ms); 
        } catch (InterruptedException e) {}
    }

    public void stop() {
        exit = true;
    }
}