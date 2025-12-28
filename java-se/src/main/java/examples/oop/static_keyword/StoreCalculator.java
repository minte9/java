/**
 * Online store checkout stystem.
 * Thre is one shared taxed rate (static variable), one shared calculator (static method), 
 * and a helper class that belongs to the store (static inner class).
 */

package examples.oop.static_keyword;

public class StoreCalculator {
    public static void main(String[] args) {
        double total = Calculator.finalPrice(100);
        System.out.println(total);  // 110.0
    }

    static double TAX_RATE = 0.10;
    static class Calculator {
        static double finalPrice(double price) {
            return price + (price * TAX_RATE); 
        }
    }
}
