package streams_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Laziness {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // NO terminal operation
        numbers.stream()
            .filter(n -> {
                return n <= 2;
            });  // nothing happens

        System.out.println(numbers);  // [1, 2, 3]

        // TERMINAL operation
        numbers = numbers.stream()
            .filter(n -> {
                return n <= 2;
            })
            .collect(Collectors.toList());  // triggers evaluation

        System.out.println(numbers);  // [1, 2]
    }
}