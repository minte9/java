### What is a Thread?

A thread is a lightweight `unit of execution` inside a process.  

Processes are isolated (separate memory space).  
Threads run inside the `same process` and share memory.  

From a user's perspective, threads may feel like separate programs.  

Threads share the same memory (heap), but each thread has:  
- its own call stack
- its own execution path

Every Java application starts with a `single thread`, the main thread.  
The main thread starts execution at main().

When a new thread is `started`: 
- a new call stack is created
- execution begin at run()

Calling start() creates a new thread.  
Calling run() directly `does not` create a thread.  

~~~java
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
~~~

