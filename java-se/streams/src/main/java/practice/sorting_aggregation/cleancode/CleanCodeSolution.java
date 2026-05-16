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
