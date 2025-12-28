/**
 * Map real-world example:
 * 
 * Count how many times each word appears.
 * This is the #1 real use of maps in data processing.
 * 
 * Real use-case:
 * - Word counters
 * - Click counters
 * - Duplicate detection
 * - Log analysis
 */

package examples.collections.maps;

import java.util.HashMap;
import java.util.Map;

public class WordsCounter {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana"};

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        System.out.println(freq);  // {banana=2, orange=1, apple=2}
    } 
}
