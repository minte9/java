/**
 * STREAMS - FOREACH VS COLLECT
 * --------------------------------
 * forEach() is for side efects.
 * collect() is for producing a result.
 * 
 * TIPICAL SIDE EFFECTS:
 * ---------------------
 *  - printing, logging
 *  - writing a file
 *  - sending emails
 *  - mutating external state (usually discouraged)
 * 
 * COLLECT:
 * ---------------------
 *  - Terminal operation
 *  - Returns a new data structure
 *  - No side effects (ideally)
 * 
 * COMMON COLLECTORS:
 * ------------------
 *  - toList()
 *  - toSet()
 *  - toMap()
 *  - joining()
 *  - groupBy()
 */

package com.minte9.streams.collect;

import java.util.List;
import java.util.stream.Collectors;

public class Collect {
    public static void main(String[] args) {
        
        // ------------------------
        // FOREACH - SIDE EFFECTS
        // ------------------------
        List<String> users = List.of("Alice", "Bob", "Charlie");

        users.stream()
             .forEach(user -> System.out.println("Processing user: " + user));

        /*
            Processing user: Alice
            Processing user: Bob
            Processing user: Charlie
         */


        // ------------------------
        // COLLECT - toList()
        // ------------------------
        List<String> clients = List.of("Alice", "Bob", "Charlie");

        List<String> activeClients = 
            clients.stream()
                .filter(user -> user.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(activeClients);
        
        /*
            [Alice]
        */
    }   
}
