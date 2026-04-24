### What is a Thread?

A thread is a lightweight `unit of execution` inside a process.  

Processes are isolated (separate memory space).  
Threads run inside the `same process` and share memory.  

From a user's perspective, threads may feel like separate programs.  

Threads share the same memory (heap), but each thread has:  
- its own call stack
- its own execution path

Every Java application starts with a `single thread`, the main thread.  
The main thread starts execution at main().

When a new thread is `started`: 
- a new call stack is created
- execution begin at run()

Calling start() creates a new thread.  
Calling run() directly `does not` create a thread.  

~~~java
package threads.theory;

public class MainThread {
    public static void main(String[] args) {
    
        // main thread
        String name = Thread.currentThread().getName();
        System.out.println(name); // main

        // other threads
        Thread w1 = new Worker();
        Thread w2 = new Worker();
        w1.start();  // Thread-0
        w2.start();  // Thread-1
    }    
}

class Worker extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
~~~

### When to use Threads

Use threads when you need `multiple` things to make progress at the same time.  

1. Use threads when the task `waits` a lot (good case).  

~~~java
// Waiting for network, database, files, APIs, messages, etc.

callBankApi();
readLargeFile();
processRabbitMqMessage();
queryDatabase();
~~~

2. Use threads when work can run `independently` (good case).   
But only if shared data is protecte correctly.  

~~~java
// Each transaction can be processed independently
processTransaction(tx1);
processTransaction(tx2);
processTransaction(tx3);

synchronized (account) {
    account.withdraw(amount);
}
~~~

3. Use threads for `background` work (good case).  
The user or server should not wait unnecessary.  

~~~java
// Do not block the main application
sendEmail();
generateReport();
writeAuditLog();
~~~

### When to NOT use threads

1. Do not use threads when code `must` happen in order (bad case).  
This sequence has strict order, threads may make it harder and unsafe.  

~~~java
validateOrder();
chargeCard();
shipProduct();
~~~

2. Do `not` use raw threads for many tasks.  
For real application prefer `ExecutorService`.    
Because ExecutorService controls `how many` threads are created.  

~~~java
new Thread(task).start();  // NOK
new Thread(task).start();
new Thread(task).start();
~~~
~~~java
ExecutorService executor = Executors.newFixedThreadPool(10);  // OK
executor.submit(task);
~~~

3. For backend Java/Spring Boot, you usually do not manually create threads.  
Servers like Tomcat already use thread pools to handle HTTP requests.  

You use threads more directly for background jobs, message consumers, 
batch processing, or stress-test clients.
