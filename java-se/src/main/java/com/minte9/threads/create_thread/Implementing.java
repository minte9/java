/**
 * Thread implementing Runnable.
 * Using lambdas the code is more concise and easy to read.
 */

package com.minte9.threads.create_thread;

public class Implementing {    
    public static void main(String[] args) 
                        throws InterruptedException {

        System.out.println(Thread.currentThread().getName()); // main

        new Thread(new MyClass()).start(); // Tread-0

        new Thread(new Runnable() {
            public void run() {
                System.out.println(
                    Thread.currentThread().getName()
                );
            }
        }).start(); // Thread-1

        new Thread(() -> { // Lambdas
            System.out.println(
                Thread.currentThread().getName()
            );
        }).start(); // Thread-2

        System.out.println("Back in Main");

        /* 
            main
            Thread-0
            Thread-1
            Back in Main
            Thread-2
        */
    }
}

class MyClass implements Runnable {

    @Override public void run() {
        System.out.println(
            Thread.currentThread().getName()
        );
    }
}