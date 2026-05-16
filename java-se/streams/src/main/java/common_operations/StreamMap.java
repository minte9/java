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