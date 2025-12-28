/**
 * Preventing Duplicate Logins / Usernames
 * 
 * Imagine you have a system where new users sign up with a username.
 * You want to quickly detect duplicates in memory to avoid 
 * unnecessary database hits.
 * 
 * Why HashSet? Fast O(1) lookups, no need for sorting. 
 */

package examples.collections.sets;

import java.util.HashSet;

public class UniqueUsernamesCheck {
    public static void main(String[] args) {
        HashSet<String> usernames = new HashSet<>();

        // Existing usernames in system
        usernames.add("johndoe");
        usernames.add("janedoe");

        // New username attempt
        String newUser = "johndoe";

        if (usernames.contains(newUser)) {
            System.out.println("❌ Username already taken!");
        } else {
            usernames.add(newUser);
            System.out.println("✅ Username created!");
        }
    }
    
}
