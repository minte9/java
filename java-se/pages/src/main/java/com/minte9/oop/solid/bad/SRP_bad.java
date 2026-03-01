/**
 * NOT SRP - BAD DESIGN
 * --------------------
 * OrderRepository having both save() and printInvoice()
 * Mixing persistence logic with presentation logic
 * 
 * That would give the class MULTIPLE reasons to change.
 * 
 * SOLID principle don't shine in 20-line examples.
 * They shine in 20000 line systems.
 * 
 * Imagine the project grows.
 * 
 * Repository now:
 *  - Connects to PostgreSQL
 *  - Use transactions
 *  - Use connection pooling
 *  - Handle retry logic
 *  - Logs SQL errors
 * 
 * Invoice printing now:
 *  - Generated PDF
 *  - Supports multiple currencies
 *  - Sends invoice by email
 * 
 * Now your class becomes:
 *  - 200 lines of DB logic
 *  - 150 lines of invoice formating
 */
package com.minte9.oop.solid.bad;

public class SRP_bad {
    public static void main(String[] args) {
        Order order = new Order("ORD-1", 150.0);

        Repository<Order> repository = new Bad_OrderRepository();
        repository.save(order);
        repository.print(order);
            // Order saved:ORD-1
            // Invoice printed: ORD-1 / 150.0
    }
}

record Order(String id, double amount) {}

interface Repository<T> {
    void save(T entity);
    void print(T entity);
}

class Bad_OrderRepository implements Repository<Order> {
    @Override
    public void save(Order order) {
        System.out.println("Order saved:" + order.id());
    }
    @Override
    public void print(Order order) {
        System.out.println("Invoice printed: " + order.id() + " / " + order.amount());
    }
}
