/**
 * Creating threads by implementing Runnable.
 * 
 * Real-life analogy:
 *  - Imagine a restaurant 
 *  - Each customer order can be prepared independently
 *  - The chef (main program) doesn't cook everything personally
 *  - Instead, the chef hands tasks to kitchen workers who work in parallel
 * 
 * In Java:
 *  - The task = Runnable
 *  - The worker = Thread
 */

package main.java.com.minte9.threads.creating_threads;

public class Implementing {  // Restaurant App
    public static void main(String[] args) {

        Runnable order1 = new OrderPreparation("Pizza");
        Runnable order2 = new OrderPreparation("Pasta");        
        Runnable order3 = new OrderPreparation("Salad");  
        
        Thread worker1 = new Thread(order1, "Chef-1");
        Thread worker2 = new Thread(order2, "Chef-2");
        Thread worker3 = new Thread(order3, "Chef-3");

        worker1.start();
        worker2.start();
        worker3.start();

        /*
            Output (order may vary):
            ========================
            Chef-2 is preparing Pasta
            Chef-1 is preparing Pizza
            Chef-3 is preparing Salad
            Chef-2 finished Pasta
            Chef-3 finished Salad
            Chef-1 finished Pizza
        */
    } 
}

class OrderPreparation implements Runnable {

    private final String orderName;
    public OrderPreparation(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        
        System.out.println(threadName + " is preparing " + orderName);
        try {
            // Simulate time needed to prepare the order
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(threadName + " finished " + orderName);
    }
}
