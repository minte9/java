/**
 * Payments System / Refundable payments
 * Not all payments types support refunds, so only those that do will implement this interface.
 */

package examples.oop.interfaces;

public class PaymentsRefundable {
    public static void main(String[] args) {

        Payment creditCardPayment = new CreditCardPayment("1234-5678-9012-3456");
        creditCardPayment.processPayment(150.00);  // CreditCard processing payment of $150.0

        Payment payPalPayment = new PayPalPayment("user@example.com");
        payPalPayment.processPayment(75.50);  // PayPall processing payment of $75.5

        // Refund example
        if (creditCardPayment instanceof Refundable) {
            ((Refundable) creditCardPayment).refund(50.00);  // Refunding $50.0 to Credit Card.
        }

        if (payPalPayment instanceof Refundable) {
            ((Refundable) payPalPayment).refund(20.00);  // Refunding $20.0 via PayPal.
        }
    }
}

// Abstract superclass
abstract class Payment {
    public abstract void processPayment(double amount);
    public abstract boolean validatePayment();
}

// Interface for refundable payments
interface Refundable {
    void refund(double amount);
}

// Subclass
class CreditCardPayment extends Payment implements Refundable {
    private String cardNumber;

    public CreditCardPayment(String s) {
        this.cardNumber = s;
    }

    @Override
    public void processPayment(double amount) {
        if (!validatePayment()) {
            System.out.println("CreditCard validation failed!");
        } else {
            System.out.println("CreditCard processing payment of $" + amount);  // processing OK
        }
    }

    @Override
    public boolean validatePayment() {
        return cardNumber != null && cardNumber.length() == 19;  // simple validation
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunding $" + amount +" to Credit Card.");
    }
}

// Subclass
class PayPalPayment extends Payment implements Refundable {
    private String email;

    public PayPalPayment(String s) {
        this.email = s;
    }

    @Override
    public void processPayment(double amount) {
        if (!validatePayment()) {
            System.out.println("PayPal validation failed!");
        } else {
            System.out.println("PayPall processing payment of $" + amount);  // processing OK
        }
    }

    @Override
    public boolean validatePayment() {
        return email != null && email.contains("@"); // simple validation
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunding $" + amount +" via PayPal.");
    }
}