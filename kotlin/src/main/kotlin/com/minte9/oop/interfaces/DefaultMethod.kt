/**
 * A class that implements interfaces must override abstract method,
 * but can skip the default ones.
 *
 * We add a new default method without breaking existing classes.
 */

package com.minte9.oop.interfaces

fun main() {
    val creditCardProcessor = CreditCardProcessor()
    val payPalProcessor = PayPalProcessor()

    creditCardProcessor.processPayment(20.0)  // Processing CreditCard payment: 20.0
    creditCardProcessor.refundPayment(10.0)  // Refunding CreditCard payment: 10.0

    payPalProcessor.processPayment(40.0)  // Processing PayPal payments: 40.0
    payPalProcessor.refundPayment(20.0)  // Refund not supported by this processor!
}

interface PaymentProcessor {
    fun processPayment(amount: Double)
    fun refundPayment(amount: Double) {
        println("Refund not supported by this processor!")
    }
}

class CreditCardProcessor: PaymentProcessor {
    override fun processPayment(amount: Double) {
        println("Processing CreditCard payment: $amount")
    }
    override fun refundPayment(amount: Double) {
        println("Refunding CreditCard payment: $amount")
    }
}

class PayPalProcessor: PaymentProcessor {
    override fun processPayment(amount: Double) {
        println("Processing PayPal payments: $amount")
    }
}