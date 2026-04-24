package threads.theory;

public class MainThread {
    public static void main(String[] args) {
    
        // main thread
        String name = Thread.currentThread().getName();
        System.out.println(name); // main

        // other threads
        Thread w1 = new Worker();
        Thread w2 = new Worker();
        w1.start();  // Thread-0
        w2.start();  // Thread-1
    }    
}

class Worker extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}