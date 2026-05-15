### ExecutorService

Creating a thread is an expensive operation and it should be minimized.  
ExecutorService is a `Java framework` for managing and executing threads efficiently. 

ExecutorService allows you to run asynchron tasks without having to deal with threads directly.  
ExecutorService creates the `thread pool` only once, minimizing the overhead.  

Decouples task execution from thread management,  
making concurrent programming `safer`, cleaner, and more scalable.  

Fixed pool size `prevents` CPU exhausion and latency spikes.  
One bad trade doesn't crash the system.  

~~~java
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
~~~

### Named Threads

A more realistic improvement is using named threads.    
In finance, `thread observability` is crucial.  

~~~java
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
~~~

