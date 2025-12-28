/**
 * Autocomplete Suggestions (Alphabetically Sorted List)
 * 
 * The search engine needs: 
 *      - no duplicates
 *      - sorted list
 *      - fast insert and retrival
 * 
 * Why TreeSet: 
 *      - sorted ordering all the time
 *      - unique values
 *      - logarithmic performance still very good
 */

package examples.collections.sets;

import java.util.Set;
import java.util.TreeSet;

public class AutocompleteSuggestions {
    public static void main(String[] args) {
        Set<String> suggestions = new TreeSet<>();
        suggestions.add("Laptop");
        suggestions.add("Lamp");
        suggestions.add("Latte");
        suggestions.add("Laptop");  // duplicate, ignored
        suggestions.add("Laser");

        // Always alphabetically sorted
        System.out.println(suggestions);  // Lamp, Laptop, Laser, Latte

        // Example: find the first suggestion starting with "La"
        suggestions.stream()
                   .filter(s -> s.startsWith("La"))
                   .forEach(System.out::println);
                        // Lamp
                        // Laptop
                        // Laser
                        // Latte
    }
    
}
