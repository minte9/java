package streams_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonOperations {
    public static void main(String[] args) {
        
        // FILTER
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers = 
            numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Filter: " + numbers);  // [2, 4, 6]


        // MAP
        List<String> names = Arrays.asList("john", "jane", "jack");
        names = 
            names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Mapping: " + names);  // [JOHN, JANE, JACK]
        

        // SUM
        List<Integer> nums = Arrays.asList(10, 20, 30);
        int sum = 
            nums.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum: " + sum);  // 60


        // SORTED
        List<Integer> items = Arrays.asList(5, 2, 8, 1);
        items = 
            items.stream()
                .sorted()
                .collect(Collectors.toList());
                
        System.out.println("Sorting: " + items);  // [1, 2, 5, 8]


        // COLLECT
        List<String> employee = Arrays.asList("john", "jane", "mary", "jack");
        employee = 
            employee.stream()
                .filter(name -> name.startsWith("j"))
                .collect(Collectors.toList());

        System.out.println("Collect: " + employee);  // [john, jane, jack]


        // REDUCE
        List<Integer> quantities = Arrays.asList(10, 20, 30);
        int total = 
            quantities.stream()
                .reduce(0, (a, b) -> a + b);
        
        System.out.println("Reduce: " + total);  // 60
    }
}