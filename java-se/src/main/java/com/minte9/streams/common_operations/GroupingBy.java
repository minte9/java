/**
 * Stream groupingBy ...
 * 
 * Groupingby to use whatever keys you want for spliting.
 * Use first char as key (example) 
 */

package com.minte9.streams.common_operations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingBy {
    public static void main(String[] args) {

      Map<Object, List<String>> map;
      map = Stream.of("ab", "bc", "ac", "bd")
            .collect(Collectors.groupingBy(
                x -> x.charAt(0)
            ))
        ;
      
        System.out.println(map); // {a=[ab, ac], b=[bc, bd]}
        System.out.println(map.get('a')); // [ab, ac]
        System.out.println(map.get('b')); // [bc, bd]
    }
}