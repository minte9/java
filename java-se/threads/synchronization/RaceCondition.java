/**
 * Two or more threads may have access to a single object's data.
 * Not good.
 */

package threads.synchronization;

public class RaceCondition {    
    public static void main(String[] args) {
    
        ConcurentRunner runner = new ConcurentRunner();

        Thread a = new Thread(runner, "Alpha");
        Thread b = new Thread(runner, "Beta");
        a.start();
        b.start();

        /* 
            Beta sess balance : 100
            Alpha sess balance : 100
            Alpha withdraws 80, new balance = -60
            Beta withdraws 80, new balance = 20
        */
    }    
}

class ConcurentRunner implements Runnable {
    private int balance = 100;

    @Override public void run() {
        withdraw();
    }

    private void withdraw() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " sess balance : " + balance); 
        balance -= 80;  // NOT atomic

        System.out.println(threadName + " withdraws 80, new balance = " + balance);
    }
}
