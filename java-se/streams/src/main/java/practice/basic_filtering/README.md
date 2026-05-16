## Streams Practice - LEVEL 1

## Basic Filtering

From the list of employees:

Task 1 - Return all employees with salary greater than 5000  
Task 2 - Return only the names of employees from "IT" department  
Task 3 - Calculate the total salary of all employees  

You must implement: imperative & stream version

Starter Application:

~~~java
import java.util.ArrayList;
import java.util.List;

public class Level1Practice {

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


### 1.1 Imperative Solution

~~~java
/**
 * Employees with salary greater than 5000
 * ================================================
 * Imperative Solution
 */
package practice.basic_filtering.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeSolution {

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
        List<Employee> group1 = new ArrayList<>(); 
        for(Employee employee : employees) {
            if (employee.salary() > 5000) {
                group1.add(employee);
            }
        }
        for(Employee employee : group1) {
            System.out.println(employee);
        }
        /*
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=4, name=Diana, department=Finance, salary=5500]
        */

        System.out.println("Task 2: Employees from IT department");
        List<Employee> group2 = new ArrayList<>();
        for(Employee employee : employees) {
            if (employee.department().equals("IT")) {
                group2.add(employee);
            }
        }
        for (Employee employee : group2) {
            System.out.println(employee);
        }
        /*
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=6, name=Frank, department=IT, salary=4500]
        */

        System.out.println("Task 3: Total salary of all employees");
        int sum = 0;
        for(Employee employee : employees) {
            sum += employee.salary();
        }
        System.out.println(sum);
        
        /*
            30000
        */
    }
}

record Employee(int id, String name, String department, int salary) {}
~~~


### 1.2 Stream Solution

~~~java
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

        System.out.println("Task 3 - Calculate the total salary of all employees");

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
~~~


### 1.3 Clean Code Solution

~~~java
/**
 * Employees with salary greater than 5000
 * ================================================
 * Clean Code Solution
 * 
 * Better than Stream Solution:
 *   - because StreamSolution still puts everything in main()
 * 
 * Here responsibilities are separated. 
 */
package practice.basic_filtering.cleancode;

import java.util.List;

public class CleanCodeSolution {

    public static void main(String[] args) {

        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employees = employeeRepository.getEmployees();
        EmployeeService employeeService = new EmployeeService(employees);

        System.out.println("Task 1: Employees with salary greater than 5000");

        List<Employee> salaryFiltered = employeeService.finEmployeesWithSalaryAtLeast(5000);
        salaryFiltered.forEach(System.out::println);
        /*
            Employee[id=1, name=Alice, department=IT, salary=6000]
            Employee[id=3, name=Charlie, department=IT, salary=7000]
            Employee[id=4, name=Diana, department=Finance, salary=5500]
        */    
       
        System.out.println("Task 2: Employee's names from IT department");

        List<String> names = employeeService.findNameByDepartment("IT");
        names.forEach(System.out::println);
        /*
            Alice
            Charlie
            Frank
        */

        System.out.println("Task 3: Calculate the total salary of all employees");
        int sum = employeeService.getSalarySum();
        System.out.println(sum);

        /*
            30000
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

    public List<Employee> finEmployeesWithSalaryAtLeast(int salary) {
        return employees.stream()
                .filter(employee -> employee.salary() >= salary)
                .toList();
    }

    public List<String> findNameByDepartment(String department) {
        return employees.stream()
                .filter(employee -> department.equals(employee.department()))  // safer
                    // because department might be null
                    // null.equals("IT") throws exception
                .map(Employee::name)
                .toList();
    }

    public int getSalarySum() {
        return employees.stream()
                .mapToInt(Employee::salary)  // less verbose
                .sum();
    }
}
~~~