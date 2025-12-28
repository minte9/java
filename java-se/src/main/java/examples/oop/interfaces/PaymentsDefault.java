/**
 * Payments System / Refund are not supported
 * We add a new default method without breaking existing classes.
 */
package examples.oop.interfaces;

public class PaymentsDefault {
    public static void main(String[] args) {
        CreditCardProcessor c = new CreditCardProcessor();
        c.processPayment(20.0);  // Processing CreditCard payment: 20.0
        c.refundPayment(10.0);  // Refunding CreditCard payment: 10.0

        PayPalProcessor p = new PayPalProcessor();
        p.processPayment(30.0);  // Processing PayPal payment: 30.0
        p.refundPayment(15.0);  // Refund not supported by this processor!
    }
}

interface PaymentProcessor {
    void processPayment(double amount);

    // New method with default implementation
    default void refundPayment(double amout) {
        System.out.println("Refund not supported by this processor!");
    }
}

class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing CreditCard payment: " + amount);
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Refunding CreditCard payment: " + amount);
    }
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: " + amount);
    }

    // No need to implement refundPayments() unless we want custom behavior
}
