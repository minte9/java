/**
 * STREAMS - MOST COMMON OPERATIONS
 * --------------------------------
 * Filter:
 *  - Select elements based on a condition (Predicate)
 * 
 * Mapping:
 *  - Transform each element into another form 
 * 
 * Sorting:
 *  - Sort elements (natural or custom comparator)
 * 
 * Collecting:
 *  - Convert stream into a collection or other result
 *  
 */
package com.minte9.streams.streams_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsOperations {
    public static void main(String[] args) {
        
        // Filter
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);  // 2 4 6

        // Mapping   
        List<String> names = Arrays.asList("john", "jane", "jack");
        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);  // JOHN JANE JACK
        
        List<Integer> nums = Arrays.asList(1, 2, 3);
        int sum = nums.stream()
                      .mapToInt(Integer::intValue)
                      .sum();
        System.out.println("Sum: " + sum);  // 6

        // Sorting
        List<Integer> items = Arrays.asList(5, 2, 8, 1);
        items.stream()
             .sorted()
             .forEach(System.out::println);  // 1 2 5 8

        // Collecting
        List<String> filtered = 
            names.stream()
                 .filter(name -> name.startsWith("j"))
                 .collect(Collectors.toList());
        System.out.println(filtered);  // [john, jane, jack]
    }
}
