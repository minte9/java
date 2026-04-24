### What is a Thread?

A thread is a lightweight unit of execution inside a process.  
Threads share the same memory (heap), but each thread has:  
- its own call stack
- its own execution path

Every Java application starts with a single thread, the main thread.
The main thread starts execution at main().

~~~
 public static void main(String[] args) {
    
    // main thread
    String name = Thread.currentThread().getName();
    System.out.println(name); // main

    // other threads
    Thread w1 = new Worker();
    w1.start();

    // with lambdas
    Runnable task = () -> {
        System.out.println(Thread.currentThread().getName());  // Thread-0
    };
    Thread t = new Thread(task, "Task-1");
    t.start();
}
~~~

### Process vs Thread

Processes are isolated (separate memory space).  
Threads run inside the same process and share memory.  
From a user's perspective, threads may feel like separate programs.  

### Run

When a new thread is started: 
- a new call stack is created
- execution begin at run()

Calling start creates a new thread.  
Calling run directly does not create a thread.  

### Implementing Runnable

This is the prefered way to create threads in Java. 

Java supports single inheritance (you can extend only one class).  
Interface implementation allows better design and reuse.

Runnable separates:
- what should run -> Runnable (task)
- how it runs -> Thread (execution)

### Functional Interface

Runnable is a functional interface.

It has a single abstract method run().
This makes it compatible with:
- anonymous classes
- lambda expressions (Java 8+)

Runnable represents a task, not a thread.  
Lambdas make Runnable concise.  
Execution order is unpredictable.  