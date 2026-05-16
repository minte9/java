package collectors;

import java.util.List;
import java.util.stream.Collectors;

public class CollectorsToList {
    public static void main(String[] args) {
        
        // forEach() - side effects
        List<String> users = List.of("Alice", "Bob", "Charlie");
        users.stream()
             .forEach(user -> 
                System.out.println("Processing user: " + user)
            );
            /*
                Processing user: Alice
                Processing user: Bob
                Processing user: Charlie
            */

        // Collect - toList()
        List<String> clients = List.of("Alice", "Bob", "Charlie");

        List<String> filteredClients = 
            clients.stream()
                .filter(user -> user.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(filteredClients);  // [Alice]
    }   
}
