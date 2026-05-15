### Creating Threads

There are `two ways` to create a new thread of execution.  
One way is to declare a class as a subclass of `Thread`.  
The `recommended` one is to implement Runnable.  

### Extending Thread class

The Thread class implements Runnable interface.  
The subclass should override the `run` method of the interface.    

 ~~~java
package threads.creating_threads;

public class Extending {
    public static void main(String[] args) {

        // main thread
        String name = Thread.currentThread().getName();
        System.out.println(name);

        // other threads
        Thread w1 = new Worker();
        Thread w2 = new Worker();
        Thread w3 = new Worker();
        w1.start();
        w2.start();
        w3.start();

        // with lambdas
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        Thread t = new Thread(task, "Task-1");
        t.start();

        /*  
            Output (order may vary):
            ========================
            main
            Thread-0
            Thread-2
            Thread-1
            Task-1
            Thread-2
            Thread-0
            Thread-1
        */
    }
}

class Worker extends Thread { // Look Here

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            Thread.sleep(1000);
            System.out.println(threadName);
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}
    }
}
~~~

### Implementing Runnable

Java supports single inheritance (you can extend only one class).  
Interface implementation allows `better` design and reuse.

The recommended alternativ (`modern Java`) is to implement Runnable directly.  

Runnable `separates`:
- what should run -> Runnable (task)
- how it runs -> Thread (execution)

Runnable is a `functional` interface.

It has a `single` abstract method run().
This makes it compatible with:
- anonymous classes
- lambda expressions (Java 8+)

Runnable represents a task, `not` a thread.  
Lambdas make Runnable concise.  
Execution order is unpredictable. 

~~~java
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
 * 
 * This shows true concurrency - orders are prepared in parallel, not sequentially.  
 */

package threads.creating_threads;

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
~~~

