package com.minte9.threads.creating_threads;

public class Extending {
    public static void main(String[] args) {

        // main thread
        String name = Thread.currentThread().getName();
        System.out.println(name);

        // other threads
        Thread w1 = new Worker();
        Thread w2 = new Worker();
        Thread w3 = new Worker();
        w1.start();
        w2.start();
        w3.start();

        // with lambdas
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        Thread t = new Thread(task, "Task-1");
        t.start();

        /*  
            Output (order may vary):
            ========================
            main
            Thread-0
            Thread-2
            Thread-1
            Task-1
            Thread-2
            Thread-0
            Thread-1
        */
    }
}

class Worker extends Thread { // Look Here

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            Thread.sleep(1000);
            System.out.println(threadName);
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}
    }
}
