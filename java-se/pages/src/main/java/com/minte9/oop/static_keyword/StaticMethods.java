/**
 * Static methods belong to the class and not every instance.
 * You do not need a specific object in order to use static methods.
 * Omitting public keyword makes the class, method or property package-private.
 */

package com.minte9.oop.static_keyword;

public class StaticMethods {
    public static void main(String[] args) {

        System.out.println(CurrencyConverter.dollarsToEuro(10));  // 9.0
        System.out.println(CurrencyConverter.dollarsToEuro(50));  // 45.0
    }
}

class CurrencyConverter {
    static double dollarsToEuro(double amount) {
        return amount * 0.9;  // simplified exchange rate
    }
}