package threads.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TradingEngine_NamedThreads {

    public static void main(String[] args) {

        ExecutorService tradeExecutor = Executors.newFixedThreadPool(
            2, 
            new TradeThreadFactory()  // Look Here
        );

        // Simulate incomming trades
        tradeExecutor.submit(new TradeTask(101, "AAPL", 182.45, 100));
        tradeExecutor.submit(new TradeTask(102, "EUR/USD", 1.0845, 1_000_000));
        tradeExecutor.submit(new TradeTask(103, "BTC/USD", 64250.10, 2));
        tradeExecutor.submit(new TradeTask(104, "MSFT", 415.20, 5));
        tradeExecutor.shutdown();

        /*
            Output (order varies):
            ======================
            [trade-worker-2] Executing trade 102: EUR/USD, 1000000 @ 1.08
            [trade-worker-1] Executing trade 101: AAPL, 100 @ 182.45
            [trade-worker-1] Trade 101 completed
            [trade-worker-2] Trade 102 completed
            [trade-worker-1] Executing trade 103: BTC/USD, 2 @ 64250.10
            [trade-worker-2] Executing trade 104: MSFT, 5 @ 415.20
            [trade-worker-1] Trade 103 completed
            [trade-worker-2] Trade 104 completed
        */
    }
}

class TradeTask implements Runnable {

    private final long tradeId;
    private final String symbol;
    private final double price;
    private final int quantity;

    public TradeTask(long tradeId, String symbol, double price, int quantity) {
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

class TradeThreadFactory implements ThreadFactory {
    
    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("trade-worker-" + counter.getAndIncrement());  // Look Here
        return t;
    }
}