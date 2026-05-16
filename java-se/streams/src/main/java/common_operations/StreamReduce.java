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
