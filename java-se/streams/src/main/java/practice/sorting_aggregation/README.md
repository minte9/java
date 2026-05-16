## Streams Practice - LEVEL 2

## Sorting & Aggregation

Using the dataset below, implement BOTH:

- imperative solution
- stream solution

Task 1: Return all employees sorted by salary descending   
Task 2: Find highest paid employee  
Task 3: Group employees by department  
Task 4: Calculate average salary of all employees  

Starter Application:

~~~java
import java.util.List;

public class Level2Practice {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", 6000),
                new Employee(2, "Bob", "HR", 4000),
                new Employee(3, "Charlie", "IT", 7000),
                new Employee(4, "Diana", "Finance", 5500),
                new Employee(5, "Eve", "HR", 3000),
                new Employee(6, "Frank", "IT", 4500)
        );

    }
}

record Employee(int id, String name, String department, int salary) {}
~~~


### 2.1 Imperative Solution

~~~java
/**
 * Employees sorted by salary descending
 * =====================================
 * Imperative Solution
 * 
 * List.of() is immutable.
 * For sort() we need mutable list.
 */
package practice.sorting_aggregation.imperative;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImperativeSolution {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(  // mutable list
                new Employee(1, "Alice", "IT", 6000),
                new Employee(2, "Bob", "HR", 4000),
                new Employee(3, "Charlie", "IT", 7000),
                new Employee(4, "Diana", "Finance", 5500),
                new Employee(5, "Eve", "HR", 3000),
                new Employee(6, "Frank", "IT", 4500)
        ));

        System.out.println("Task 1: Employees sorted by salary descending");
        employees.sort(new EmployeeComparator());
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        /*
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=4, name=Diana, department=Finance, salary=5500]
            Employee[id=6, name=Frank, department=IT, salary=4500]
            Employee[id=2, name=Bob, department=HR, salary=4000]
            Employee[id=5, name=Eve, department=HR, salary=3000]
        */

        System.out.println("Task 2: Find highest paid employee.");
        Employee highest = employees.get(0);
        for (Employee employee : employees) {
            if (employee.salary() > highest.salary()) {
                highest = employee;
            }
        }
        System.out.println(highest);
        /*
            Employee[id=3, name=Charlie, department=IT, salary=7000]
        */

        System.out.println("Task 3: Group employees by department");
        Map<String, List<Employee>> grouped = new HashMap<>();
        for (Employee employee : employees) {
            if (!grouped.containsKey(employee.department())) {
                grouped.put(employee.department(), new ArrayList<>());
            }
            grouped.get(employee.department()).add(employee);
        }
        for (Map.Entry<String, List<Employee>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey());
            for (Employee employee : entry.getValue()) {
                System.out.println(employee);
            }
        }
        /*
            Finance
            Employee[id=4, name=Diana, department=Finance, salary=5500]
            HR
            Employee[id=2, name=Bob, department=HR, salary=4000]
            Employee[id=5, name=Eve, department=HR, salary=3000]
            IT
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=6, name=Frank, department=IT, salary=4500]
        */

        System.out.println("Task 3: Group employees by department (modern Java)");
        Map<String, List<Employee>> mGrouped = new HashMap<>();
        for (var employee : employees) {
            mGrouped.computeIfAbsent(
                employee.department(), 
                key -> new ArrayList<>()
            ).add(employee);
        }
        for (var entry : mGrouped.entrySet()){
            System.out.print(entry.getKey() + ": ");

            for (var employee : entry.getValue()) {
                System.out.print(employee.name() + " ");
            }

            System.out.println();
        }
        /*
            Finance: Diana
            HR: Bob Eve
            IT: Charlie Alice Frank
        */

        System.out.println("Task 4: Average salary of all employees");
        int total = 0;
        for (Employee employee : employees) {
            total += employee.salary();
        }
        double avg = (double) total/employees.size();
        System.out.println(avg);
        /*
            5000.0
        */
    }
}

record Employee(int id, String name, String department, int salary) {}

class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Integer.compare(e2.salary(), e1.salary());  // descending order
    }

}
~~~


### 2.2 Stream Solution

~~~java
/**
 * Employees sorted by salary descending
 * =====================================
 * Stream Solution
 * 
 * Stream sorting does NOT mutate the list.
 * There is not need for new ArrayList<>(List.of(..))
 */
package practice.sorting_aggregation.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

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

        System.out.println("Task 1: Employees sorted by salary descending");
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


        System.out.println("Task 2: Find highest paid employee.");
        Employee highest = 
            employees.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .toList()
                .get(0);
        System.out.println(highest);
        /*
            Employee[id=3, name=Charlie, department=IT, salary=7000]
        */

        System.out.println("Task 3: Group employees by department");
        Map<String, List<Employee>> grouped = 
            employees.stream()
                .collect(Collectors.groupingBy(Employee::department));

        grouped.forEach((department, employeesList) -> {
            System.out.print(department + ": ");

            employeesList.forEach(employee -> {
                System.out.print(employee.name() + " ");
            });
            System.out.println();
        });
        /*
            Finance: Diana
            HR: Bob Eve
            IT: Alice Charlie Frank
        */


        System.out.println("Task 4: Average salary of all employees");
        double avg = 
            employees.stream()
                .mapToInt(Employee::salary)
                .average()
                .orElse(0.0);
        System.out.println(avg);
        /*
            5000.0
        */

    }
}

record Employee(int id, String name, String department, int salary) {}
~~~


### 2.3 Clean Code Solution

~~~java
/**
 * Employees sorted by salary descending
 * =====================================
 * Clean Code Solution
 * 
 * .toList() returns an unmodifiable list.
 * You can't use sorted.add(..)
 * 
 * This is VERY good for thread safety and defensive programming.
 */
package practice.sorting_aggregation.cleancode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CleanCodeSolution {

    public static void main(String[] args) {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(
            repository.getEmployees()
        );


        System.out.println("Task 1: Employees sorted by salary descending");
        List<Employee> sorted = service.findEmployeesOrderedBySalaryDescending();
        sorted.forEach(System.out::println);
        /*
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=4, name=Diana, department=Finance, salary=5500]
            Employee[id=6, name=Frank, department=IT, salary=4500]
            Employee[id=2, name=Bob, department=HR, salary=4000]
            Employee[id=5, name=Eve, department=HR, salary=3000]
        */


        System.out.println("Task 2: Find highest paid employee.");
        Employee highest = service.findEmployeeWithHighestSalary();
        System.out.println(highest);
        /*
            Employee[id=3, name=Charlie, department=IT, salary=7000]
        */


        System.out.println("Task 3: Group employees by department");
        Map<String, List<Employee>> grouped = 
            service.getEmployeesGroupedByDepartment();

        grouped.forEach((department, employeesList) -> {
            System.out.println(
                department + " -> " +
                employeesList.stream()
                    .map(Employee::name)
                    .collect(Collectors.joining(", "))
            );
        });
        /*
            Finance -> Diana
            HR -> Bob, Eve
            IT -> Alice, Charlie, Frank
        */


        System.out.println("Task 4: Average salary of all employees");
        double avg = service.getAverageSalary();
        System.out.println(avg);
        /* 
            5000.0
        */
    }
}

// Domain
record Employee(int id, String name, String department, int salary) {}

// Repository
class EmployeeRepository{
    public List<Employee> getEmployees() {
        return List.of(
                new Employee(1, "Alice", "IT", 6000),
                new Employee(2, "Bob", "HR", 4000),
                new Employee(3, "Charlie", "IT", 7000),
                new Employee(4, "Diana", "Finance", 5500),
                new Employee(5, "Eve", "HR", 3000),
                new Employee(6, "Frank", "IT", 4500)
        );
    }
}

// Service
class EmployeeService {
    private final List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> findEmployeesOrderedBySalaryDescending() {
        return employees.stream()
            .sorted(Comparator.comparing(Employee::salary).reversed())
            .toList();
    }
    
    public Employee findEmployeeWithHighestSalary() {
        return employees.stream()
            .sorted(Comparator.comparing(Employee::salary).reversed())
            .toList()
            .get(0);
    }

    public Map<String, List<Employee>> getEmployeesGroupedByDepartment() {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::department));
    }

    public double getAverageSalary(){
        return employees.stream()
            .mapToInt(Employee::salary)
            .average()
            .orElse(0.0);
    }
}
~~~