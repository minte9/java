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
