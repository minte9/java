/**
 * L - Liskov Substitution Principle (LSP)
 * ---------------------------------------
 * Subtypes must be substituable for their base types.
 * 
 * Example: Payment System
 * 
 * PaymentMethod defines a contract:
 *  - pay(amount)
 *  - amount must be positive
 * 
 * All implementation:
 *  - validate the amount
 *  - perform payment
 *  - behave consistently
 * 
 * All payment types behave consistently. 
 */

package com.minte9.oop.solid;

public class LSP {
    public static void main(String[] args) {
        PaymentMethod p1 = new CreditCardPayment();
        PaymentMethod p2 = new PayPalPayment();

        p1.pay(200);
        p2.pay(300);
    }
}

interface PaymentMethod {
    void pay(double amount);
}

abstract class BasePayment implements PaymentMethod {
    protected void validate(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }
}

class CreditCardPayment extends BasePayment {
    @Override
    public void pay(double amount) {
        validate(amount);
        System.out.println("Paid $" + amount + " using Credit Card");
    }
}

class PayPalPayment extends BasePayment {
    @Override
    public void pay(double amount) {
        validate(amount);
        System.out.println("Paid $" + amount + " using PayPal");
    }
}
