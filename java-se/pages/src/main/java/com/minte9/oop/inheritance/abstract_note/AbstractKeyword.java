package com.minte9.oop.inheritance.abstract_note;

public class AbstractKeyword {

    public static void main(String[] args) {

        CreditCardPayment payment = new CreditCardPayment();

        payment.setAmount(250);
        payment.processPayment();  // Processing credit card payment of $250.0
    }
}

abstract class Payment {
    protected double amount;

    public abstract void processPayment();  // abstract (must be implemented)

    public void setAmount(double amount) {  // non-abstract method (shared behavior)
        this.amount = amount;
    }
}

class CreditCardPayment extends Payment {

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment of $" + amount);
    }
}