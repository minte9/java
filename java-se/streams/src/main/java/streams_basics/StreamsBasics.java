package streams_basics;

import java.util.Arrays;
import java.util.List;

public class StreamsBasics {
    public static void main(String[] args) {

        List<String> names = 
            Arrays.asList("John", "Jane", "Doe", "Julia");

        System.out.println("Traditional way - imprerative");
        for (String name : names) {
            if (name.startsWith("J")) {
                System.out.print(name + " ");  // John Jane Julia
            }
        }

        System.out.println("Stream way - declarative");
        names.stream()
             .filter(x -> x.startsWith("J"))
             .forEach(System.out::print);  // John Jane Julia
    }
}