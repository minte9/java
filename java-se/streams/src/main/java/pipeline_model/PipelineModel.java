package pipeline_model;

import java.util.Arrays;
import java.util.List;

public class PipelineModel {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        long total = numbers.stream()            // SOURCE
                            .filter(n -> n > 2)  // INTERMEDIATE
                            .map(n -> n * 2)     // INTERMEDIATE
                            .count();            // TERMINAL
                            
        System.out.println(total);  // 3
    }
}
