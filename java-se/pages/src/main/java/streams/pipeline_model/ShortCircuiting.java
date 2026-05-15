/**
 * STREAMS - SHORT-CIRCUITING OPERATIONS
 * -------------------------------------
 * Short-circuiting terminal operations may stop the stream
 * before all elements are processed.
 * 
 * COMMON SHORT-CIRCUITING OPERATIONS
 * ----------------------------------
 *  - findFirst()
 *  - findAny()
 *  - anyMatch()
 *  - allMatch()
 *  - noneMatch()
 * 
 * These operations benefits directly from lazy operations 
 * 
 * FIND FIRST SCENARIO:
 * --------------------
 * Given a list of orders sorted by creation time,
 * find the FIRST unpaid (active) order.
 * 
 * Once the first match is found, the stream stops. 
 */

package com.minte9.streams.pipeline_model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ShortCircuiting {
    public static void main(String[] args) {
        
        List<Order> orders = Arrays.asList(
            new Order(1, true),
            new Order(2, true),
            new Order(3, false),  // first unpaid order
            new Order(4, true)
        );

        Optional<Order> firstUnpaid = 
            orders.stream()
                   .filter(order -> {
                        System.out.println("checking order " + order.id());
                        return !order.paid();
                   })
                   .findFirst();

        firstUnpaid.ifPresent(
            order -> System.out.println("First unpaid order: " + order)
        );

        /*
            checking order 1
            checking order 2
            checking order 3
            First unpaid order: Order[id=3, paid=false]
         */
    }
}

/**
 * Simple domain record
 */
record Order(int id, boolean paid) {}
