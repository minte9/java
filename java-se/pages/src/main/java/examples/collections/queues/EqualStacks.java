/**
 * EQUAL STACKS - PROBLEM
 * ----------------------
 * Find the maximum possible height of the stacks such that 
 * all of the stacks are exactly the same height.
 */

package examples.collections.queues;

import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;

public class EqualStacks {
    public static void main(String[] args) {
        
        List<Integer> h1 = List.of(3, 2, 1, 1, 1);
        List<Integer> h2 = List.of(4, 3, 2);
        List<Integer> h3 = List.of(1, 1, 4, 1);

        Deque<Integer> d1 = new ArrayDeque<>(h1);
        Deque<Integer> d2 = new ArrayDeque<>(h2);
        Deque<Integer> d3 = new ArrayDeque<>(h3);

        int s1 = d1.stream().mapToInt(Integer::intValue).sum();
        int s2 = d2.stream().mapToInt(Integer::intValue).sum();
        int s3 = d3.stream().mapToInt(Integer::intValue).sum();

        int res = 0;
        while (s1 > 0 && s2 > 0 && s3 > 0) {
            if (s1 == s2 && s2 == s3) {
                res = s1;
                break;
            }

            if (s1 >= s2 && s1 >= s3) {
                s1 -= d1.pollFirst();
            } else
            if (s2 >= s1 && s2 >= s3) {
                s2 -= d2.pollFirst();
            } else {
                s3 -= d3.pollFirst();
            }
        }

        System.out.println("Equal stacks height: " + res);  // 5

    }
}
