/**
 * Payment systems / CreditCard & PayPal
 * Payment class defines common behavior, while subclasses implement specific logic.
 * Adding new payment methods (CryptoPayment) is easy without changing existing code.
 */

package examples.oop.inheritance;

public class PaymentsSystem{
    public static void main(String[] args) {

        Payment p1 = new CreditCardPayment("1234-5678-9012-3456");
        p1.processPayment(150.00);  // CreditCard processing payment of $150.0

        Payment p2 = new PayPalPayment("user@example.com");
        p2.processPayment(75.50);  // PayPall processing payment of $75.5
    }
}

// Abstract superclass
abstract class Payment {
    public abstract void processPayment(double amount);
    public abstract boolean validatePayment();
}

// Subclass
class CreditCardPayment extends Payment {
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
}

// Subclass
class PayPalPayment extends Payment {
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
}
