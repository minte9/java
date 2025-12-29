/**
 * STREAM - PARTITIONBY
 * -----------------------------------------------------
 * Partitioning a Stream into two collections of values.
 */

package com.minte9.streams.common_operations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningBy {
    public static void main(String[] args) {

      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

      Map<Boolean, List<Integer>> map = numbers
            .stream()
            .collect(Collectors.partitioningBy(
                x -> x > 3
            ));
        
      System.out.println(map.get(true)); // [4, 5, 6, 7, 8, 9, 10]
      System.out.println(map.get(false)); // [1, 2, 3]
    }
}