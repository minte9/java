## Streams Basics

Streams were introduced in Java 8 to process collections in a 
functional and declarative style.

Common operations: filter, map, collect, reduce  
Key ideas:  

- A stream is not a data storage (not a collection)
- It works with pipelines
- Supports lazy evaluation
- Encurage declarative programming

Streams are not about iteration - they are about transformation pipelines. 

~~~java
package streams_basics;

import java.util.Arrays;
import java.util.List;

public class StreamsBasics {
    public static void main(String[] args) {

        List<String> names = 
            Arrays.asList("John", "Jane", "Doe", "Julia");

        System.out.println("Traditional way - imprerative");
        for (String name : names) {
            if (name.startsWith("J")) {
                System.out.print(name + " ");  // John Jane Julia
            }
        }

        System.out.println("Stream way - declarative");
        names.stream()
             .filter(x -> x.startsWith("J"))
             .forEach(System.out::print);  // John Jane Julia
    }
}
~~~

### 1. Iteration Styles

Streams allow us to write collection-processing code at a higher level of abstraction.
In programming there are TWO iterative approaches:

External iteration (imperative):
- The developer controls HOW iteration happens 
- Uses loops or iterators
- Requires mutable state (counter, variables)
 
Internal iteration (declarative):
- The library controls HOW iteration hapens
- The developer specifies WHAT should be done
- No explicit loops or mutable counters
 
Iterator - external iterator  
Stream   - internal iterator  

~~~java
package streams_basics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterationStyles {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int count;

        // FOR-EACH LOOP (external iteration)
        count = 0;
        for (int n : numbers) {
            if (n <= 2) count++;
        }
        System.out.println("For-each loop: " + count);  // 2

        // ITERATOR (external iteration)
        count = 0;
        Iterator<Integer> it = numbers.iterator();
        while(it.hasNext()) {
            int n = it.next();

            if (n <= 2) count++;
        }
        System.out.println("Iterator: " + count);  // 2

        // STREAM (internal iteration)
        long total = numbers.stream()
                .filter(n -> n <= 2)
                .count();
        System.out.println("Stream: " + total);  // 2
    }    
}
~~~


### 2. Common Operations

Streams most common operations:

- filter = Select elements based on a condition (Predicate)
- mapping = Transform each element into another form 
- sorting = Sort elements (natural or custom comparator)
- collecting = Convert stream into a collection or other result
- reduce = Combine elements into a single result

~~~java
package streams_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonOperations {
    public static void main(String[] args) {
        
        // FILTER
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers = 
            numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Filter: " + numbers);  // [2, 4, 6]


        // MAP
        List<String> names = Arrays.asList("john", "jane", "jack");
        names = 
            names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Mapping: " + names);  // [JOHN, JANE, JACK]
        

        // SUM
        List<Integer> nums = Arrays.asList(10, 20, 30);
        int sum = 
            nums.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum: " + sum);  // 60


        // SORTED
        List<Integer> items = Arrays.asList(5, 2, 8, 1);
        items = 
            items.stream()
                .sorted()
                .collect(Collectors.toList());
                
        System.out.println("Sorting: " + items);  // [1, 2, 5, 8]


        // COLLECT
        List<String> employee = Arrays.asList("john", "jane", "mary", "jack");
        employee = 
            employee.stream()
                .filter(name -> name.startsWith("j"))
                .collect(Collectors.toList());

        System.out.println("Collect: " + employee);  // [john, jane, jack]


        // REDUCE
        List<Integer> quantities = Arrays.asList(10, 20, 30);
        int total = 
            quantities.stream()
                .reduce(0, (a, b) -> a + b);
        
        System.out.println("Reduce: " + total);  // 60
    }
}
~~~


### 3. Laziness

Streams are evaluated lazily.  

Key ideas:  

- A stream does NOT process data when it is created
- Intermediate operations (filter, map) are lazy
- Nothing happens until a TERMINAL operation is invoked
 
Terminal operations:  

- count()
- forEach()
- collect()
- findFirst()
- anyMatch()

Without a terminal operation, a stream does nothing.  
When we write numbers.strea().filter(...) we are only describing a pipeline.  

~~~java
package streams_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Laziness {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // NO terminal operation
        numbers.stream()
            .filter(n -> {
                return n <= 2;
            });  // nothing happens

        System.out.println(numbers);  // [1, 2, 3]

        // TERMINAL operation
        numbers = numbers.stream()
            .filter(n -> {
                return n <= 2;
            })
            .collect(Collectors.toList());  // triggers evaluation

        System.out.println(numbers);  // [1, 2]
    }
}
~~~


### 4. Application Example

Real-life scenario.  
Order processing application.  

~~~java
package streams_basics.app_example;

record Order(String id, double amount, boolean paid) {};
~~~

### 4.1 Traditional (for-loop implementation)

Why developers use for-loop traditionally: 

- Easy to understand
- Explicit logic
- Debug-fiendly

Limitations (real project pain):

- Boilerplate (loop + if + accumulator)
- Harder to extend pipeline
- Mutability (total variable)

~~~java
package streams.basics.traditional;

import java.util.Arrays;
import java.util.List;

public class OrdersApp {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("01", 100, true),
            new Order("02", 200, false),
            new Order("03", 300, true)
        );

        double total = 0;
        for (Order order : orders) {
            if (order.isPaid()) {
                total += order.getAmount();
            }
        }

        System.out.println("Total paid amount: " + total);  // 400.0

    }
}
~~~


### 4.2 Bad Stream Implementation (common mistake)

What's wrong here:

- Using streams like a loop (forEach)
- This is just a loop replacement
- Mutable state hack

Big red flag:

- Breaks functional style
- Not thread-safe
- Upgly and error-prone

Real industry smell:

- If you have forEach + mutation, you are misusing streams.  

~~~java
package streams.basics.bad;

import java.util.Arrays;
import java.util.List;

public class OrdersApp {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("01", 100, true),
            new Order("02", 200, false),
            new Order("03", 300, true)
        );

        final double[] total = {0};  // hack for mutation in lambda

        orders.stream()
                .filter(order -> {
                    return order.isPaid();
                })
                .forEach(order -> {
                    total[0] += order.getAmount();  // ❌ side effect
                });

        System.out.println("Total paid amount: " + total[0]);  // 400.0
    }
}
~~~


### 4.3 Correct Stream Implementation

Why this is correct:

- Declarative (reads like business logic)
- No mutation (no shared state, no hacks)
- Performance-friendly (can be parallelized safely)
- Composable (easy to extend)

~~~java
package streams.basics;

import java.util.Arrays;
import java.util.List;

public class OrdersApp {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("01", 100, true),
            new Order("02", 200, false),
            new Order("03", 300, true)
        );

        double total = orders.stream()
                .filter(Order::isPaid)
                .mapToDouble(Order::getAmount)
                .sum();

        System.out.println("Total paid amount: " + total);  // 400.0
    }
}
~~~