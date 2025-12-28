/**
 * Company Employees at work (polymorphism example).
 * Different employees can all work, but each one works in a different way. 
 */

package examples.oop.polymorphism;

public class CompanyEmployees {
    public static void main(String[] args) {

        Employee e1 = new Developer();
        Employee e2 = new Designer();

        // Same method, different behavior
        System.out.println(e1.work());  // Writing code
        System.out.println(e2.work());  // Creating designs

        // Accesing subtype-only behavior with cast
        if (e1 instanceof Developer) {
            System.out.println(((Developer) e1).debug());  // Debugging code
        }
        if (e2 instanceof Designer) {
            System.out.println(((Designer) e2).draw());  // Drawing layouts
        }
    }
}

// Superclass
abstract class Employee {
    public abstract String work();
}

// Subclass 1
class Developer extends Employee {
    @Override
    public String work() {
        return "Writing code";
    }

    public String debug() {
        return "Debugging code";
    }
}

// Subclass 2
class Designer extends Employee {
    @Override
    public String work() {
        return "Creating designs";
    }

    public String draw() {
        return "Drawing layouts";
    }
}
