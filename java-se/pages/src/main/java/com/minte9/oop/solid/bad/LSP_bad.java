/**
 * LSB - BAD EXAMPLE
 * -----------------
 * Here, a subtype changes expected behavior.
 * It throws an exception where the base type contract 
 * says it should process the payment.
 */
package com.minte9.oop.solid.bad;

public class LSP_bad {
    public static void main(String[] args) {
        PaymentMethod p1 = new CreditCardPayment();
        PaymentMethod p2 = new FreeTrialPayment();  // problematic subtype

        p1.pay(200);

        // This will break at runtime
        p2.pay(300);
    }
}

interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        System.out.println("Paid $" + amount + " using Credit Card");
    }
}

/**
 * Violates LSP
 * 
 * This class strengthens preconditions:
 * It does NOT allow payments above 100.
 * 
 * The base contract does not define such limitation.
 */
class FreeTrialPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {

        if (amount > 100) {
            throw new UnsupportedOperationException(
                "FreeTrial cannot process payments above 100");
        }

        System.out.println("Free trial payment processed: $" + amount);
    }
}

