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