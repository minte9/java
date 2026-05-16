### Pipeline Model

A stream pipeline consists of: 

1) SOURCE:

    - Creates the stream (collection, array, file, etc)

2) INTERMEDIATE OPERATIONS:

    - Transform or filter elements
    - Lazy (nothing happens yet)

3) TERMINAL OPERATIONS:

    - Produces a result or side-effect
    - Triggers execution of the pipeline 

~~~java
package pipeline_model;

import java.util.Arrays;
import java.util.List;

public class PipelineModel {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        long total = numbers.stream()            // SOURCE
                            .filter(n -> n > 2)  // INTERMEDIATE
                            .map(n -> n * 2)     // INTERMEDIATE
                            .count();            // TERMINAL

        System.out.println(total);  // 3
    }
}
~~~
 
Even through the code is written top-to-bottom,  
exection happens element-by-element, not step-by-step.  

Logical execution:

~~~sh
1 -> filter -> skip
2 -> filter -> skip
3 -> filter -> map -> count
4 -> filter -> map -> count
5 -> filter -> map -> count
~~~

Stream DO NOT:

- filter all elements first
- then map all elements

Why this matters:  

- Streams are efficient
- No temporary collections
- Minimal memory usage
- Short-circuiting is possible
- Parallel execution became feasible



### Short-Circuiting Operations

Short-circuiting terminal operations may stop the stream before all elements are processed.  

Commonm Short-Circuting operations:

- findFirst()
- findAny()
- anyMatch()
- allMatch()
- noneMatch()
 
These operations benefits directly from lazy operations.

~~~java
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
~~~