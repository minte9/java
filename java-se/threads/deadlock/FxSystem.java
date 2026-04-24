/**
 * Deadlock-safe bank transfer
 * ===========================
 * Problem:
 *  - Transfer needs to lock TWO accounts.
 *  - If two threads lock accounts in different order, deadlock can happen. 
 * 
 * Solution:
 *  - Always lock accounts in a consistent order.
 *  - Here we lock by account ID: smaller first, bigger second.
 */

public class FxSystem {
    public static void main(String[] args) {
        
        Account accountA = new Account(1, 1000);
        Account accountB = new Account(2, 1000);

        BankService bankService = new BankService();

        Thread t1 = new Thread(() -> {
            bankService.transfer(accountA, accountB, 100);
        });

        Thread t2 = new Thread(() -> {
            bankService.transfer(accountB, accountA, 200);
        });

        t1.start();
        t2.start();

        try {
            t1.join();  // wait for this thread to finish before continuing
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Account A balance: " + accountA.getBalance());
        System.out.println("Account B balance: " + accountB.getBalance());

        /*
            Transferred 100 from account 1 to account 2 
            Transferred 200 from account 2 to account 1 
            Account A balance: 1100
            Account B balance: 900
        */
    }
}

class BankService {

    public void transfer(Account from, Account to, int amount) {

        Account firstLock;
        Account secondLock;

        // Always choose the same lock order
        if (from.getId() < to.getId()) {
            firstLock = from;
            secondLock = to;
        } else {
            firstLock = to;
            secondLock = from;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {

                if (from.getBalance() < amount) {
                    System.out.println(
                        "Not enough money in account " + from.getId());
                    return;
                }

                from.withdraw(amount);
                to.deposit(amount);

                System.out.printf(
                    "Transferred %d from account %d to account %d %n",
                    amount, from.getId(), to.getId()
                );
            }
        }
    }
}

class Account {
    private final int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;  // DB transaction in real-systems
    }

    public void deposit(int amount) {
        balance += amount;
    }
}