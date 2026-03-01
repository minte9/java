/***
 * S - SINGLE RESPONSIBILITY PRINCIPLE (SRP)
 * ---------------------------------------------
 * A class should have only ONE reason to change.
 * 
 * That means:
 *  - A class should have only one responsibility
 *  - It should focus on a single concern
 *  - Business logic, persistence, and presenation should NOT
 *    be mixed together
 * 
 * Example: Order Processing System
 * 
 * Design (Correct SRP):
 *  - Order             -> holds data only
 *  - OrderRepository   -> handle persistence (save to DB, file, etc)
 *  - InvoiceService    -> handle invoice printing (display logic)
 * 
 * Why?
 *  - If database logic changes       -> onlye OrderRepository changes
 *  - If invoice format changes       -> only InvoiceService changes
 *  - If order data structure changes -> only Order changes 
 * 
 * BAD DESIGN (Violates SRP):
 *  - OrderRepository having both save() and printInvoice()
 *  - Mixing persistence logic with presentation logic
 * 
 * That would give the class MULTIPLE reasons to change.
 */
package com.minte9.oop.solid;

public class SRP {
    public static void main(String[] args) {
        Order order = new Order("ORD-1", 150.0);

        Repository<Order> repository = new OrderRepository();
        repository.save(order);
            // Order saved:ORD-1

        InvoiceService consoleInvoiceService = new ConsoleInvoiceService();
        consoleInvoiceService.printInvoice(order);
            // Invoice printed: ORD-1 / 150.0
    }
}

record Order(
    String id,
    double amount
) {}

interface Repository<T> {
    void save(T entity);
}

class OrderRepository implements Repository<Order> {
    @Override
    public void save(Order order) {
        System.out.println("Order saved:" + order.id());
    }
}

interface InvoiceService<T> {
    void printInvoice(T entity);
}

class ConsoleInvoiceService implements InvoiceService<Order> {
    @Override
    public void printInvoice(Order order) {
        System.out.println("Invoice printed: " + order.id() + " / " + order.amount());
    }
}