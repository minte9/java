/**
 * Threads have names (default or given by you).
 * We don't know how often the threads will switch.
 * 
 * Two or more threads may have access to a single object's data.
 * We need a lock for access.
 */

package com.minte9.threads.lock;

public class ConcurentApp {    
    public static void main(String[] args) {
    
        ConcurentRunner runner = new ConcurentRunner();
        Thread a = new Thread(runner, "Alpha");
        Thread b = new Thread(runner, "Beta");
        a.start();
        b.start();

        /* 
            Account Balance: 20
            Account Balance: 20
            Beta --- withdraw --- 10
            Account Balance: 0
            Alpha --- withdraw --- 10 // Not good!
            Account Balance: 0
        */
    }    
}

class ConcurentRunner implements Runnable {
    private int accountBalance = 20;

    @Override public void run() {
        withdraw(10);
        withdraw(10);
    }

    private void withdraw(int amount) {
        System.out.println(
            "Account Balance: " + accountBalance
        ); 
        if (accountBalance >= amount) { 
            sleep(500);
            accountBalance = accountBalance - amount;
            System.out.println(
                Thread.currentThread().getName() + " --- withdraw --- 10"
            );
        }
    }

    private void sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds); // sleep - threads take turns
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }
    }
}