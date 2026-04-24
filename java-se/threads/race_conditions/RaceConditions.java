/**
 * RACE CONDITION - INCORRECT VERSION
 * ----------------------------------
 * 
 * PROBLEM:
 * --------
 * Two threads access and modify the same shared data (accountBalance) 
 * without synchronization.
 * 
 * This example intentionally contains a BUG.
 * 
 * EXPECTED BEHAVIOR (LOGIC):
 * --------------------------
 * Initial balance: 20
 * Two threads withdraw 10 twice -> balance should never go below 0.
 * 
 * ACTUAL BEHAVIOR (BUG):
 * ----------------------
 * Both threads see the same balance (20).
 * Both think the can withdraw.
 * The result is an invalid double-withdraw.
 * 
 * This is a CLASSIC race condition.
 * 
 * IMPORTANT RULE:
 * ----------------------------------------------------------------------
 * If multiple threads read and write shared data without synchronization, 
 * the program is incorrect - even if it "seems to work".
 */

package com.minte9.threads.race_conditions;

public class RaceConditions {
    public static void main(String[] args) {
    
        ConcurentRunner r = new ConcurentRunner();

        // Two threads sharing the SAME runner instance
        Thread a = new Thread(r, "Alpha");
        Thread b = new Thread(r, "Beta");

        a.start();
        b.start();

        /* 
            Typical output (order may vary):

            Account Balance: 20
            Account Balance: 20
            Beta --- withdraw --- 10
            Alpha --- withdraw --- 10 // ❌ BUG (should not happen)
        */
    }   
}

class ConcurentRunner implements Runnable {

    // Shared mutable state (NOT thread-safe)
    private int accountBalance = 20;

    @Override 
    public void run() {
        withdraw(10);
        withdraw(10);
    }

    /** 
     * This method is not synchronized.
     * Multiple threads can execute it at the same time. 
     */
    private void withdraw(int amount) {

        // Both threads may print the same balance
        System.out.println(
            "Account Balance: " + accountBalance
        ); 

        // CHECK (not atomic)
        if (accountBalance >= amount) {
            
            // Artificial delay to increase change of race condition
            // Makes the bug visible and reproducible
            sleep(500);

            // ACT (update shared state)
            accountBalance -= amount;

            System.out.println(
                Thread.currentThread().getName() 
                + " --- withdraw --- " + amount
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