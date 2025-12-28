/**
 * ANNONYMOUS INNER CLASS vs LAMBDA - REAL LIFE EXAMPLE
 * ----------------------------------------------------
 * Scenario:
 *  We have a list of orders and want to sort them by price.
 * 
 * This example demonstrate:
 *   1) Sorting with an anonymous inner class (pre-Java 8)
 *   2) Sorting with a lambda expression (Java 8+)
 * 
 * Both approaches pass behavior (comparition logic) to the sort() method.
 */

package com.minte9.lambdas.anonymous_inner_classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RealExample {
    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Book", 15.99));
        orders.add(new Order("Laptop", 1200.));
        orders.add(new Order("Pen", 1.50));

        // -------------------------------------
        // 1) Anonymous Inner Class (pre-Java 8)
        // --------------------------------------
        orders.sort(
            new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return Double.compare(o1.price(), o2.price());
                }
            }
        );
        System.out.println("Sorted with Inner Class:");
        orders.forEach(System.out::println);

        // -------------------------------------
        // 2) Lambda Expressions (Java 8+)
        // --------------------------------------
        orders.sort(
            (o1, o2) -> Double.compare(o1.price(), o2.price())
        );
        System.out.println("Sorted using lambda:");
        orders.forEach(System.out::println);
    }
}

record Order(String name, double price) {}
