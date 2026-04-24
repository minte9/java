/**
 * Trade Processing with ExecutorService
 * =====================================
 * Trading platform:
 *  - Incomming orders arrive continuously
 *  - Each order can be processed independently
 * 
 * ExecutorService give us:
 * =======================
 *  - Controlled concurrency
 *  - Thread reuse
 *  - Predictable resource usage
 */

package threads.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradingEngine {

    private static final int WORKER_COUNT = 2;

    public static void main(String[] args) {

        ExecutorService tradeExecutor = Executors.newFixedThreadPool(WORKER_COUNT);

        // Simulate incomming trades
        tradeExecutor.submit(new TradeExecutionTask(101, "AAPL", 182.45, 100));
        tradeExecutor.submit(new TradeExecutionTask(102, "EUR/USD", 1.0845, 1_000_000));
        tradeExecutor.submit(new TradeExecutionTask(103, "BTC/USD", 64250.10, 2));
        tradeExecutor.submit(new TradeExecutionTask(104, "MSFT", 415.20, 5));

        tradeExecutor.shutdown();

        /*
            Output (order varies):
            ======================
            [pool-1-thread-1] Executing trade 101: AAPL, 100 @ 182.45
            [pool-1-thread-2] Executing trade 102: EUR/USD, 1000000 @ 1.08
            [pool-1-thread-1] Trade 101 completed
            [pool-1-thread-1] Executing trade 103: BTC/USD, 2 @ 64250.10
            [pool-1-thread-2] Trade 102 completed
            [pool-1-thread-2] Executing trade 104: MSFT, 5 @ 415.20
            [pool-1-thread-1] Trade 103 completed
            [pool-1-thread-2] Trade 104 completed
        */
    }
}

class TradeExecutionTask implements Runnable {

    private final long tradeId;
    private final String symbol;
    private final double price;
    private final int quantity;

    public TradeExecutionTask(long tradeId, String symbol, double price, int quantity) {
        this.tradeId = tradeId;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.printf(
            "[%s] Executing trade %d: %s, %d @ %.2f%n", 
            threadName, tradeId, symbol, quantity, price
        );

        try {
            // Simulate: validation, risk checks, persistence
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("[%s] Trade %d interrupted %n", threadName, tradeId);
        }

        System.out.printf("[%s] Trade %d completed %n", threadName, tradeId);
    }
}
