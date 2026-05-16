## Collectors

forEach() is for side efects.  
collect() is for producing a result.  

Tipical side effects:

- printing, logging
- writing a file
- sending emails
- mutating external state (usually discouraged)

Collect: 

- Terminal operation
- Returns a new data structure
- No side effects (ideally)

Common Collectors:

- toList()
- toSet()
- toMap()
- joining()
- groupBy()

~~~java
package collectors;

import java.util.List;
import java.util.stream.Collectors;

public class CollectorsToList {
    public static void main(String[] args) {
        
        // forEach() - side effects
        List<String> users = List.of("Alice", "Bob", "Charlie");
        users.stream()
             .forEach(user -> 
                System.out.println("Processing user: " + user)
            );
            /*
                Processing user: Alice
                Processing user: Bob
                Processing user: Charlie
            */

        // Collect - toList()
        List<String> clients = List.of("Alice", "Bob", "Charlie");

        List<String> filteredClients = 
            clients.stream()
                .filter(user -> user.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(filteredClients);  // [Alice]
    }   
}
~~~


### Grouping (real-world example)

Imagine an e-commerce system.

You want to group orders by status so you can:

- process payments
- prepare shipments
- show dashboards

~~~java

package collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectGroupingBy {
    public static void main(String[] args) {
        
        List<Order> orders = List.of(
            new Order(1, Status.NEW),
            new Order(2, Status.PAID),
            new Order(3, Status.NEW),
            new Order(4, Status.SHIPPED)            
        );

        /**
         * GROUPING - PLAIN JAVA
         * ======================
         * Manual grouping using loops and mutable state.
         *  - Verbose
         *  - Easy to forget edge cases
         *  - Hard to read
         *  - Manual state management
         */
        Map<Status, List<Order>> ordersByStatus = new HashMap<>();

        for (Order order : orders) {
            Status status = order.status();

            if (!ordersByStatus.containsKey(status)) {
                ordersByStatus.put(status, new ArrayList<>());
            }
            
            ordersByStatus.get(status).add(order);
        }
    
        System.out.println(
            ordersByStatus.get(Status.NEW)
        );
        // [Order[id=1, status=NEW], Order[id=3, status=NEW]]

            
        /**
         * GROUPING - STREAMS
         * ==================
         * Groups stream elements by a classifier function.
         */
        Map<Status, List<Order>> ordersByStatus_B =
            orders.stream()
                  .collect(groupingBy(Order::status));

        System.out.println(
            ordersByStatus_B.get(Status.PAID)
        );
        // [Order[id=2, status=PAID]]


        /**
         * GROUPING - STREAMS (very common variant):
         * -----------------------------------------
         * Grouping + Counting
         */
        Map<Status, Long> countByStatus =
            orders.stream()
                  .collect(groupingBy(
                        Order::status,
                        counting()
                  ));

        System.out.println(countByStatus);
        // {SHIPPED=1, NEW=2, PAID=1}
    }
    
}

enum Status {
    NEW, PAID, SHIPPED
}

record Order(int id, Status status) {}
~~~