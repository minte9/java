/**
 * Employees with salary greater than 5000
 * ================================================
 * Stream Solution
 * 
 * Better than Imperative Solution:
 *   - shorter
 *   - less mutable
 *   - less error-prone
 *   - easier to compose with filter, map, sorted, limit, collect
 *   - closer to business language: "filter employees by salary"
 * 
 * NOT clean code because main() knows:
 *   - where the employees come from
 *   - how to filter the salaries
 *   - how to find IT names
 *   - how to sum salaries
 *   - how to print results
 */
package practice.basic_filtering.stream;

import java.util.List;

public class StreamSolution {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", 6000),
                new Employee(2, "Bob", "HR", 4000),
                new Employee(3, "Charlie", "IT", 7000),
                new Employee(4, "Diana", "Finance", 5500),
                new Employee(5, "Eve", "HR", 3000),
                new Employee(6, "Frank", "IT", 4500)
        );

        System.out.println("Task 1: Employees with salary greater than 5000");

        List<Employee> group1 = 
            employees.stream()
                .filter(employee -> employee.salary() >= 5000)
                .toList();

        group1.forEach(System.out::println);

        /*
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=4, name=Diana, department=Finance, salary=5500]
        */

        System.out.println("Task 2: Employees from IT department");

        List<String> group2 = 
            employees.stream()
                .filter(employee -> employee.department().equals("IT"))  // == is wrong (compares references, NOT values)
                .map(employee -> employee.name())
                .toList();

        group2.forEach(System.out::println);

        /*
            Alice
            Charlie
            Frank
        */

        System.out.println("Task 3: Total salary of all employees");

        int sum = 
            employees.stream()
                .map(employee -> employee.salary())
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(sum);

        /*
            30000
        */
                
        
    }
}

record Employee(int id, String name, String department, int salary) {}