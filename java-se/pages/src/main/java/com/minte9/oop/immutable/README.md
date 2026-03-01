# Immutability

An object is immutable if its state cannot be changed after it is created.

Once constructed, its fields never change.

Example in Java:

    String name = "John";

String is immutable — every modification creates a new object.


### How to Make a Class Immutable

In Java:

    - Make the class final
    - Make fields private final
    - No setters
    - Initialize fields in constructor

Return defensive copies for mutable fields

    final class User {
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }


### Why Is Immutability Important?

Immutable objects are automatically thread-safe.

No synchronization needed because state cannot change.

Very important in:

    - Concurrency
    - Banking systems
    - Multi-threaded backend services


## ❌ WRONG Banking Example (Mutable Account)

    /*
    * ============================================
    * WRONG DESIGN – Mutable and Not Thread-Safe
    * ============================================
    *
    * Problems:
    * 1. Uses double for money (precision errors)
    * 2. Mutable state without synchronization
    * 3. Allows illegal state (negative balance)
    * 4. Identity (IBAN) can be changed
    * 5. Race condition possible
    */

    class WrongBankAccount {

        private String iban;
        private double balance;

        public WrongBankAccount(String iban, double balance) {
            this.iban = iban;
            this.balance = balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            balance -= amount;
        }

        public void setIban(String iban) { // dangerous!
            this.iban = iban;
        }

        public double getBalance() {
            return balance;
        }
    }


### What Can Go Wrong?

🔴 Race condition

If two threads execute:

    account.withdraw(100);
    account.withdraw(200);

At the same time → balance corruption.

🔴 Invariant broken

Nothing stops:

    account.withdraw(1_000_000);

Balance can go negative.

🔴 Identity corruption

Changing IBAN after creation = catastrophic in banking.


## CORRECT Banking Example (Immutable Transaction)

In banking systems, we usually make:

    - Accounts carefully synchronized
    - Transactions immutable

    /*
    * ============================================
    * CORRECT DESIGN – Immutable Money
    * ============================================
    *
    * 1. Immutable
    * 2. Uses BigDecimal (precise)
    * 3. Safe for financial calculations
    */

    import java.math.BigDecimal;
    import java.math.RoundingMode;

    final class Money {

        private final BigDecimal amount;

        public Money(BigDecimal amount) {
            if (amount.scale() > 2) {
                amount = amount.setScale(2, RoundingMode.HALF_EVEN);
            }
            this.amount = amount;
        }

        public Money add(Money other) {
            return new Money(this.amount.add(other.amount));
        }

        public Money subtract(Money other) {
            return new Money(this.amount.subtract(other.amount));
        }

        public boolean isNegative() {
            return amount.compareTo(BigDecimal.ZERO) < 0;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return amount.toString();
        }
    }