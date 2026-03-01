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
package com.minte9.oop.solid;

public class SRPbad {
    public static void main(String[] args) {
        Order2 order = new Order2("ORD-1", 150.0);

        Repository2<Order2> repository = new OrderRepository2();
        repository.save(order);
        repository.print(order);
            // Order saved:ORD-1
            // Invoice printed: ORD-1 / 150.0
    }
}

record Order2(String id, double amount) {}

interface Repository2<T> {
    void save(T entity);
    void print(T entity);
}

class OrderRepository2 implements Repository2<Order2> {
    @Override
    public void save(Order2 order) {
        System.out.println("Order saved:" + order.id());
    }
    @Override
    public void print(Order2 order) {
        System.out.println("Invoice printed: " + order.id() + " / " + order.amount());
    }
}
