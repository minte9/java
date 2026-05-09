package com.minte9.oop.inheritance;

public class PaymentApp {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(100);
        Payment p2 = new PayPalPayment(200);

        p1.processPayment();
        p2.processPayment();

        /*
            Validating payment of $100.0
            Processing credit card payment...
            Sending receipt...

            Validating payment of $200.0
            Processing PayPal payment...
            Sending receipt...
        */
    }
}

// Superclass
abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    // Template method (shared workflow)
    public void processPayment() {
        validate();
        execute();  // subclass-specific
        sendReceipt();
    }

    public void validate() {
        System.out.println("Validating payment of $" + amount);
    }

    protected abstract void execute();  // Look Here (must be implemented)

    public void sendReceipt() {
        System.out.println("Sending receipt...\n");
    }
}

// Subclass 1
class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    protected void execute() {
        System.out.println("Processing credit card payment...");
    }
}

// Subclass 2
class PayPalPayment extends Payment {

    public PayPalPayment(double amount) {
        super(amount);
    }

    @Override
    protected void execute() {
        System.out.println("Processing PayPal payment...");
    }
}