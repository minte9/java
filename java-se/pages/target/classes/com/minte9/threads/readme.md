## Java Threads



### 📌 What is a Thread?
- A `thread` is the smallest unit of execution in a Java program.
- Multiple threads can run in the same process and `share memory`.

### 📌 Why Use Threads?
- Perform multiple tasks `concurrently` (UI + background jobs).
- Improve application `responsiveness`.
- Make better use of multi-core CPUs.


### 📌 How to Create Threads

1. Extending `Thread`
    
        class MyThread extends Thread {
            public void run() { .... }
        }
        new Thread().start();

2. Implementing `Runnable`

        class MyTask implements Runnable {
            public void run() { ... }
        }
        new Thread(new MyTask()).start();


### 📌 Thread Lifecycle (States)
- `New` - thread created but not started.
- `Runnable` - ready/waiting to run.
- `Running` - actively executing.
- `Blocked` - waiting for a lock or timeout.
- `Terminated` - finished execution.

### 📌 Key Methods
- `start()` - lanches new thread
- `run()` - what the thread executed
- `sleep()` - pause current thread
- `join()` - wait for thread to finish


### 📌 Thread Safety (Shared State)
- Threads share memory - race condition posible.
- To avoid data corruption use syncronyzed, locks, or atomic types.
- Without syncronization, results are unpredictable.


### 📌 ExecutorService (Thread Pools)
Rather than making raw threads, prefer ExecutorServie:
- Reuse threads
- Better performance for many tasks
- Easier to manage shutdown

Example:

    ExecutorService poll = Executors.newFixedThreadPool(5);

Principle: `Avoid raw` new Thread() in large applications.



#
### 💡 Active Recall Questions

1. What's the difference between `start()` and `run()`?
2. Name all thread lifecycle states.
3. When would you use ExecutorService over raw `Thread`?
4. Why do threads need syncronization?
5. What is a race condition?