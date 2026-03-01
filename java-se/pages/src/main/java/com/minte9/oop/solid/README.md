# SOLID 

Solid is a set of five OOP design principles in order to write 
clean, maintainable, flexible, and scalable code - expecially in languages like Java.



## S — Single Responsibility Principle (SRP)

A class should have only one reason to change.

### ❌ Bad Example

    class User {
        void saveToDatabase() { }
        void sendEmail() { }
    }

Too many responsibilities.

### Better

    class User { }

    class UserRepository {
        void save(User user) { }
    }

    class EmailService {
        void sendEmail(User user) { }
    }

Now each class has one responsibility.

#

## O - Open/Closed Principle (OCP)

Open for extension, closed for modification.  
Sofware entities should be open for extension, but closed for modification.  

### ❌ Bad Example

    class Discount {
        double calculate(String type) {
            if (type.equals("VIP")) return 0.2;
            if (type.equals("Regular")) return 0.1;
            return 0;
        }
    }

Adding new discount types means modifying this class.

### Better (Polymorphism)

    interface Discount {
        double calculate();
    }

    class VipDiscount implements Discount {
        public double calculate() { return 0.2; }
    }

    class RegularDiscount implements Discount {
        public double calculate() { return 0.1; }
    }

Now you can add new discounts without changing existing code.  

#

## L - Liskov Substitution Principle (LSP)

Subtypes must be subsittuable for their base types.  

If class B extends class A, then B should behave like A without breaking expectations.  

### ❌ Bad Example

    class Bird {
        void fly() { }
    }

    class Penguin extends Bird {
        void fly() {
            throw new UnsupportedOperationException();
        }
    }

A penguin is a bird, but it cannot fly - this breaks LSP.

### Better

    interface Bird { }

    interface FlyingBird extends Bird {
        void fly();
    }