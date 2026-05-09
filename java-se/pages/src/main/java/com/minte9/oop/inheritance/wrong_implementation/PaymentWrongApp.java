package com.minte9.oop.inheritance.wrong_implementation;

public class PaymentWrongApp {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(100);
        Payment p2 = new PayPalPayment(200);

        p1.processPayment();
        p2.processPayment();

        /*
            Validating $100.0
            Processing credit card...
            Sending receipt...

            Validating $200.0
            Processing PayPal...
            Sending receipt...
        */
    }
}

interface Payment  {
    void processPayment();    
}

class CreditCardPayment implements Payment {
    private double amount;

    public CreditCardPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment() {
        System.out.println("Validating $" + amount);
        System.out.println("Processing credit card...");
        System.out.println("Sending receipt...\n");
    }
}

class PayPalPayment implements Payment {
    private double amount;

    public PayPalPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment() {
        System.out.println("Validating $" + amount);
        System.out.println("Processing PayPal...");
        System.out.println("Sending receipt...\n");
    }
}
