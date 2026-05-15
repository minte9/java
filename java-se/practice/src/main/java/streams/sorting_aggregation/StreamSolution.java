/**
 * Employees sorted by salary descending
 * =====================================
 * Stream Solution
 * 
 * Stream sorting does NOT mutate the list.
 * There is not need for new ArrayList<>(List.of(..))
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StreamSolution {

    public static void main(String[] args) {

        List<Employee> employees = List.of(  // immutable
                new Employee(1, "Alice", "IT", 6000),
                new Employee(2, "Bob", "HR", 4000),
                new Employee(3, "Charlie", "IT", 7000),
                new Employee(4, "Diana", "Finance", 5500),
                new Employee(5, "Eve", "HR", 3000),
                new Employee(6, "Frank", "IT", 4500)
        );

        System.out.println("Task 1 - Employees sorted by salary descending.");

        List<Employee> sorted = 
            employees.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .toList();
        sorted.forEach(System.out::println);

        /*
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=4, name=Diana, department=Finance, salary=5500]
            Employee[id=6, name=Frank, department=IT, salary=4500]
            Employee[id=2, name=Bob, department=HR, salary=4000]
            Employee[id=5, name=Eve, department=HR, salary=3000]
        */

    }
}

record Employee(int id, String name, String department, int salary) {}
