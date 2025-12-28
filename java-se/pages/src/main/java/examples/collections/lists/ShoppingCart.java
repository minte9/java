/**
 * Shopping Card (ArrayList and List example).
 * 
 * In modern Java, list is preferred over ArrayList.
 * The #1 rule in modern Java: Code to an interface, not implementations.
 * 
 * List is an interface, ArrayList is one implementation of that interface.
 * Using List gives us more flexibility, you can change the implementation later, 
 * without breaking the current code.
 */

package examples.collections.lists;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public static void main(String[] args) {

        // Prefered: code to the List interface
        List<String> cart = new ArrayList<>();

        cart.add("milk");
        cart.add("bread");
        cart.add("eggs");
        System.out.println("Cart: " + cart);  // Cart: [milk, bread, eggs]

        // Remove item
        cart.remove("milk");
        System.out.println("Cart: " + cart);  // Cart: [bread, eggs]

        // Later, we can change the implementation without breaking code
        cart = List.of("water", "cereal");  // Unmodifiable List
        
        //cart.add("butter");  // ❌ Would now throw an exception
        System.out.println("Cart: " + cart); // Cart: [water, cereal]
    }
}
