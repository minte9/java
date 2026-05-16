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