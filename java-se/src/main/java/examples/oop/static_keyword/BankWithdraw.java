/**
 * Bank withdraw rule (without final) - bugs happens
 * Because the method withdraw() is not final, a subclass secretly changes the rule.
 */

package examples.oop.static_keyword;

public class BankWithdraw {
    public static void main(String[] args) {
        HackAccount acc = new HackAccount();
        acc.withdraw(1000);
        System.out.println(acc.balance);  // -900 😱
    }    
}

class BankAccount {
    protected double balance = 100;

    // ❌ NOT final — dangerous
    public boolean withdraw(double amount) {  // this method should be final
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Now, a careless (or malicious) programmer extends the class
class HackAccount extends BankAccount {

    @Override
    public boolean withdraw(double amount) {
        balance -= amount; // allows unlimited withdraws
        return true;
    }
}
