/**
 * One of the best ways to help your threads take turns is ...
 * by using sleep.
 * 
 * The main thread will became the current running tread.
 */

package com.minte9.threads.sleep;

public class Sleep {    
    public static void main(String[] args) 
                        throws InterruptedException {
        
        System.out.println("Start in main");

        new Thread(new MyClass(1)).start();
        new Thread(new MyClass(2)).start();
        new Thread(new MyClass(3)).start();
        
        System.out.println("Back in main");

        /* 
            Start in main
            Back in main
            Thread 1 / run
            Thread 3 / run
            Thread 2 / run
        */
    }
}

class MyClass implements Runnable {

    int no = 0;
    public MyClass(int i) { 
        no = i; 
    }
    
    @Override public void run() {
        try {
            Thread.sleep(1000);  // Look Here

        } catch(InterruptedException e) {}
        System.out.println("Thread " + no + " / run");
    }
}