/**
 * Employees with salary greater than 5000
 * ================================================
 * Imperative Solution
 */
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

        System.out.println("Task 3 - Calculate the total salary of all employees");

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
