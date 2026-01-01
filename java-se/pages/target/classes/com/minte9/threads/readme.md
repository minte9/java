# Java Threads


## 🧩 What is a Thread?
- A `thread` is the smallest unit of execution in a Java program.
- Multiple threads can run in the same process and `share memory`.

### 📌 Why Use Threads?
- Perform multiple tasks `concurrently` (UI + background jobs).
- Improve application `responsiveness`.
- Make better use of multi-core CPUs.

### 📌 Thread Lifecycle (States)
- `New` - thread created but not started.
- `Runnable` - ready/waiting to run.
- `Running` - actively executing.
- `Blocked` - waiting for a lock or timeout.
- `Terminated` - finished execution.


## 🚀 How to Create Threads

### 1) Extending `Thread`
    class MyThread extends Thread {
        public void run() { .... }
    }
    new Thread().start();


### 2) Implementing `Runnable`

    class MyTask implements Runnable {
        public void run() { ... }
    }
    new Thread(new MyTask()).start();