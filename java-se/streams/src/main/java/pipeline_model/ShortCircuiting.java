/** 
 * FIND FIRST SCENARIO:
 * ====================
 * Given a list of orders sorted by creation time,
 * find the FIRST unpaid (active) order.
 * 
 * Once the first match is found, the stream stops. 
 */

package pipeline_model;

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
                        System.out.println("Checking order: " + order.id());
                        return !order.paid();
                   })
                   .findFirst();

        firstUnpaid.ifPresent(
            order -> System.out.println("First unpaid: " + order)
        );

        /*
            Checking order: 1
            Checking order: 2
            Checking order: 3
            First unpaid: Order[id=3, paid=false]
         */
    }
}

// Domain
record Order(int id, boolean paid) {}
