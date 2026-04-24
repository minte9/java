### Thread Lifecycle

A Java thread goes through well-defined `states during` its lifetime:
NEW -> RUNNABLE -> (WAINTING / TIME_WAINTING/ BLOCKED) -> TERMINATED

Common misconceptions:

- "RUNNABLE means running" / It means "ready to run"
- "Sleep release locks" / sleep() does NOT release locks 
- "Threads run in order" / Scheduling is unpredictable  

~~~java
package threads.thread_lifecycle;

public class ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);  // TIME_WAITING
            } catch (InterruptedException ex) {}
        });

        // Thread created but not started
        System.out.println("State after creation: " + t.getState());  // NEW

        // Thread started
        t.start();
        System.out.println("State after start(): " + t.getState());  // RUNNABLE

        // Thread sleeping
        Thread.sleep(500);
        System.out.println("State after sleep(): " + t.getState());  // TIME_WAITING 

        // Thread finish execution
        t.join();
        System.out.println("State after completion: " + t.getState());  // TERMINATED

        /*
            State after creation: NEW
            State after start(): RUNNABLE
            State after sleep(): TIMED_WAITING
            State after completion: TERMINATED
         */
    }
}
~~~

### Sleep vs Join

They solve `different` problems.
- sleep() pauses the current thread  
- join() waits for another thread to finish.  

Both `throw` InterruptedException.  
Prefere join() for correctness.  

~~~java
package threads.thread_lifecycle;

public class SleepVsJoin {
    public static void main(String[] args) throws InterruptedException {
        
        Thread worker = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Worker done");
            } catch (InterruptedException ex) {}
        });

        worker.start();

        // This pauses main, but does NOT guarantee worker is done
        Thread.sleep(500);
        System.out.println("After sleep");

        // Main thread waits for worker to finish
        worker.join();
        System.out.println("After join");
    }
}
~~~

### Thread Stop

You might need to stop a thread on runtime.  
Threads stop by `cooperating`, not by force.  
A shared flag is used to signal termination.  

Rules:
1. Threads should stop `themselves` - never be stopped externally.
2. Shared state between threads must be visible (volatile, synchronization).
3. Timing (sleep) is NOT synchronization.

~~~java
package threads.thread_lifecycle;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start main thread");

        Worker t1 = new Worker("A");
        Worker t2 = new Worker("B");
        
        new Thread(t1).start();
        new Thread(t2).start();

        Thread.sleep(2000); 

        t1.requestStop();
        t2.requestStop();

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

class Worker implements Runnable {

    private String name;

    // volatile guarantees visibility between threads
    private volatile boolean exit = false;

    public Worker(String name) { 
        this.name = name; 
    }

    @Override 
    public void run() {

        while(!exit) {
            System.out.println("Thread " + name + " / Run");
            sleep(1000);
        }
        
        System.out.println("Thread " + name + " / Exit");
    }

    public void sleep(int ms) {
        try { 
            Thread.sleep(ms); 
        } catch (InterruptedException e) {
            // ignored by simplicity
        }
    }

    public void requestStop() {
        exit = true;
    }
}
~~~