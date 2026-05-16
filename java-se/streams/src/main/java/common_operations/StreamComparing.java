/**
 * STREAM COMPARING
 * ================
 */
package common_operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamComparing {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
            new Student("John", 23),
            new Student("Mary", 30),
            new Student("Mike", 27)
        );

        Student youngest = 
            students.stream()
                .min(Comparator.comparing(x -> x.age()))
                .get();

        System.out.println(
            youngest  // Student[name=John, age=23]
        );
    }
}

record Student(String name, int age) {}