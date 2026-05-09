package com.minte9.oop.inheritance.override_note;

public class OverrideAnnotation {

    public static void main(String[] args) {
        
        CreditCardPayment payment = new CreditCardPayment();
        payment.processPayment();  // Wrong method called!
    }
}

abstract class Payment {
    public abstract void processPayment();
}

class CreditCardPayment extends Payment {

    // Typo: processsPayment instead of processPayment
    // No @Override annotation to protect us
    public void processsPayment() {
        System.out.println("Processing credit card payment...");
    }

    // This method was auto-generated later just to satisfy compilation
    @Override
    public void processPayment() {
        System.out.println("Wrong method called!");
    }
}