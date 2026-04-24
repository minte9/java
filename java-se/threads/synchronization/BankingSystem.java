package threads.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BankingSystem {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(
            2, new BankingThreadFactory()
        );

        // Simulate concurrent withdrawals
        Account account = new Account(1000);
        executor.submit(new WithdrawTask(account, 400));
        executor.submit(new WithdrawTask(account, 300));
        executor.submit(new WithdrawTask(account, 500));
        executor.submit(new WithdrawTask(account, 200));
        executor.shutdown();

        /*
            Output (order vary):
            ====================
            [bank-worker-2] TX-1 withdraw 300 / Balance before = 1000
            [bank-worker-2] TX-1 completed / Balance after = 700
            [bank-worker-1] TX-1 withdraw 400 / Balance before = 700
            [bank-worker-1] TX-1 completed / Balance after = 300
            [bank-worker-2] TX-1 FAILED / Insufficient funds, balance=300
            [bank-worker-1] TX-1 withdraw 200 / Balance before = 300
            [bank-worker-1] TX-1 completed / Balance after = 100
        */
    }
}

class Account {
    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(int amount, AtomicLong tradeId) {
       synchronized (this) {  // Look Here
            String threadName = Thread.currentThread().getName();

            if (balance >= amount) {
                System.out.printf("[%s] TX-%s withdraw %d / Balance before = %d %n",
                    threadName, tradeId, amount, balance);

                balance -= amount;

                System.out.printf("[%s] TX-%s completed / Balance after = %d %n",
                    threadName, tradeId, balance);

            } else {
                System.out.printf("[%s] TX-%s FAILED / Insufficient funds, balance=%d %n",
                    threadName, tradeId, balance);
            }
        }
    }
}

class BankingThreadFactory implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("bank-worker-" + counter.getAndIncrement());
        return t;
    }
}

class WithdrawTask implements Runnable {

    private final AtomicLong tradeId = new AtomicLong(1);
    private final Account account;
    private final int amount;

    public WithdrawTask(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        
        // Simulate processing delay (validation, logging)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("[%s] Trade %d interrupted %n", threadName, tradeId);
            return;
        }

        account.withdraw(amount, tradeId);
    }
}
