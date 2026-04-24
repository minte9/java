/**
 * Deadlock Example
 * ================
 * Two threads lock resources in different order.  
 * This creates a circular wait - DEADLOCK 
 */

public class DeadlockExample {
    
    public static final Object account1 = new Object();
    public static final Object account2 = new Object();

    public static void main(String[] args) {

        Runnable t1 = () -> {
            synchronized (account1) {
                System.out.println("Thread 1: locked account1"); 
                sleep(100);

                System.out.println("Thread 1: waiting for account2");
                synchronized(account2) {
                    System.out.println("Thread 1: locked account2");
                }
            }
        };

        Runnable t2 = () -> {
            // acquire locks in the WRONG order, different from t1 (deadlock)
            synchronized (account2) {
                System.out.println("Thread 2: locked account1"); 
                sleep(100);

                System.out.println("Thread 2: waiting for account2");
                synchronized(account1) {
                    System.out.println("Thread 2: locked account2");
                }
            }
        };

        Thread thread1 = new Thread(t1, "T1");
        Thread thread2 = new Thread(t2, "T2");
        thread1.start();
        thread2.start();

        /*
            Thread 1: locked account1
            Thread 2: locked account1
            Thread 1: waiting for account2
            Thread 2: waiting for account2

            ... and then nothing (program freezes)
        */
    }

    private static void sleep(int miliseconds) {
        try { 
            Thread.sleep(miliseconds); 
        } catch (InterruptedException e) {}
    } 
}   
