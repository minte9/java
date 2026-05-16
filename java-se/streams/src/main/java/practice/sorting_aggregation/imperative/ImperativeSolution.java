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