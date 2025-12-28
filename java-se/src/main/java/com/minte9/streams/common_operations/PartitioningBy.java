/**
 * Stream partitionBy  ...
 * 
 * Partitioning a Stream into two collections of values (example)
 */

package com.minte9.streams.common_operations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartitioningBy {
    public static void main(String[] args) {

      Map<Boolean, List<Integer>> map;
      map = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .collect(Collectors.partitioningBy(
                x -> x > 3
            ))
        ;
        
      System.out.println(map.get(true)); // [4, 5, 6, 7, 8, 9, 10]
      System.out.println(map.get(false)); // [1, 2, 3]
    }
}