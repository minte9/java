/**
 * RACE CONDITION - FIXED VERSION
 * ----------------------------------
 * 
 * SOLUTION:
 * ---------
 * We protect the critical section using `synchronized` keyword.
 * 
 * EFFECT:
 * -------
 *  - Only ONE thread at a time can execute withdraw()
 *  - The check + update become ATOMIC
 *  - Account balance can no longer be corrupted
 * 
 * IMPORTANT:
 * ----------
 * The logic did NOT change.
 * Only synchronization was added.
 */

package com.minte9.threads.race_conditions;

public class RaceConditionsFixed {
    public static void main(String[] args) {
    
        ConcurentRunnerFixed r = new ConcurentRunnerFixed();

        // Two threads sharing the SAME runner instance
        Thread a = new Thread(r, "Alpha");
        Thread b = new Thread(r, "Beta");

        a.start();
        b.start();

        /* 
            Correct output (order may vary, but logic is correct):

            Account Balance: 20
            Alpha --- withdraw --- 10
            Account Balance: 10
            Alpha --- withdraw --- 10
            Account Balance: 0
            Account Balance: 0
        */
    }   
}

class ConcurentRunnerFixed implements Runnable {

    // Shared mutable state
    private int accountBalance = 20;

    @Override 
    public void run() {
        withdraw(10);
        withdraw(10);
    }

    /** 
     * synchronized METHOD
     * -------------------
     * The lock is taken on THIS object instance.
     * Only one thread can execute this method at a time.
     */
    private synchronized void withdraw(int amount) {

        System.out.println(
            "Account Balance: " + accountBalance
        ); 

        if (accountBalance >= amount) {
            
            // Even with delay, no race condition is possible
            sleep(500);

            accountBalance -= amount;

            System.out.println(
                Thread.currentThread().getName() 
                + " --- withdraw --- " + amount
            );
        }
    }

    private void sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }
    }
}