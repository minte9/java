## Common Operations

### 1. Streams Filter

Filter only strings that starts with a number.

~~~java
/**
 * STREAM FILTER
 * =============
 */
package common_operations;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.toList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.ArrayList;

public class StreamFilter {
    public static void main(String[] args) {

        List<String> data = asList("a", "1abc", "abc1");
        
        System.out.println(
            FirstCharIsDigit_Loop(data)  // "1abc"
        );

        System.out.println(
            FirstCharIsDigit_Stream(data)  // "1abc"
        );
    }

    private static List<String> FirstCharIsDigit_Loop(List<String> lst) {
        List<String> result = new ArrayList<>();

        for (String s: lst) {
            if (isDigit(s.charAt(0))) {
                result.add(s);
            }
        }
        return result;
    }

    private static List<String> FirstCharIsDigit_Stream(List<String> lst) {
        return lst.stream()
            .filter(x -> isDigit(x.charAt(0)))  // Look Here
            .collect(toList());
    }
}
~~~


### 2. Stream Map

Map each value to uppercase.

~~~java
/**
 * STREAM MAP
 * ==========
 */
package common_operations;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;

public class StreamMap {
    public static void main(String[] args) {

        List<String> data = asList("a", "b", "c");

        System.out.println(
            toUpper_Loop(data)  // [A, B, C]
        );

        System.out.println(
            toUpper_Stream(data)  // [A, B, C]
        );
    }

    private static List<String> toUpper_Loop(List<String> lst) {
        List<String> result = new ArrayList<>();

        for (String s: lst) {
            result.add(s.toUpperCase());
        }
        return result;
    }

    private static List<String> toUpper_Stream(List<String> lst) {
        return lst.stream()
            .map(x -> x.toUpperCase())  // lambda OR
            .map(String::toUpperCase)   // method reference
            .collect(toList());
    }
}
~~~


### 3. Stream Reduce


Reduce is used when you want a single result from collection.  

For example, to calculate the sum.  

- Imperative approach: using for loop
- Declarative approach: acc is the "accumulator" (current sum)

~~~java
/**
 * STREAM REDUCE
 * =============
 */
package common_operations;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {
    public static void main(String[] args) {

        List<Integer> data = Arrays.asList(1, 2, 3);

        System.out.println(
            reduceToSum_Loop(data)
        );

        System.out.println(
            reduceToSum_Stream(data)
        );
    }

    public static int reduceToSum_Loop(List<Integer> lst) {
        int sum = 0;
        for (Integer i : lst) {
            sum += i;
        }
        return sum;
    }

    public static int reduceToSum_Stream(List<Integer> lst) {
        return lst.stream()
            .reduce(0, (acc, x) -> acc + x);
    }
}
~~~


### 4. Stream PartitioningBy

Partitioning a Stream into two collections of values.  

~~~java
/**
 * STREAM PartitionBy
 * ================== 
 */

package common_operations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamPartitioningBy {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );

        Map<Boolean, List<Integer>> map = 
            numbers.stream()
                .collect(
                    Collectors.partitioningBy(
                        x -> x > 3
                    )
                );
        
        System.out.println(
            map.get(true)   // [4, 5, 6, 7, 8, 9, 10]
        );

        System.out.println(
            map.get(false) // [1, 2, 3]
        );
    }
}
~~~


### 5. Stream Comparing

Comparing is used when you want to find minimum or maximum of an element. 

~~~java
/**
 * STREAM COMPARING
 * ================
 */
package common_operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamComparing {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
            new Student("John", 23),
            new Student("Mary", 30),
            new Student("Mike", 27)
        );

        Student youngest = 
            students.stream()
                .min(Comparator.comparing(x -> x.age()))
                .get();

        System.out.println(
            youngest  // Student[name=John, age=23]
        );
    }
}

record Student(String name, int age) {}
~~~