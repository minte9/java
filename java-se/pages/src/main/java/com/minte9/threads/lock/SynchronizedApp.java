/**
 * With 'synchronized' method ...
 * only one thread at a time can access that method.
 */

package com.minte9.threads.lock;

public class SynchronizedApp {
    public static void main(String[] args) {
    
        SynchronizedRunner runner = new SynchronizedRunner();
        Thread a = new Thread(runner, "Alpha");
        Thread b = new Thread(runner, "Beta");
        a.start();
        b.start();

        /*
            Account Balance: 20
            Account Balance: 20
            Alpha --- withdraw --- 10
            Account Balance: 10
            Beta --- withdraw --- 10 // Good!
            Account Balance: 0
        */
    }
}

class SynchronizedRunner implements Runnable {
    private int accountBalance = 20;

    @Override public void run() {
        withdraw(10);
        withdraw(10);
    }

    private synchronized void withdraw(int amount) { // Look Here
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