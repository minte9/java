/**
 * ARRAY LIST- Hourglass sum using subList()
 * *************--
 * The hourglass shape requires:
 *  - 3 numbers from the top row
 *  - 1 number from the middle row
 *  - 3 numbers from the bottom row
 * 
 * Using subList(), we extract these slices easily 
 * without indexing loops.
 */

package examples.collections.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HourglassMax {
    public static void main(String[] args) {

        // 2D list (matrix) of integers
        List<List<Integer>> arr = Arrays.asList(
            Arrays.asList(1, 1, 1, 0, 0, 0),
            Arrays.asList(0, 1, 0, 0, 0, 0),
            Arrays.asList(1, 1, 1, 0, 0, 0),
            Arrays.asList(0, 0, 2, 4, 4, 0),
            Arrays.asList(0, 0, 0, 2, 0, 0),
            Arrays.asList(0, 0, 1, 2, 4, 0)
        );

        // Fist window
        // ***********--
        List<Integer> tmp = new ArrayList<>();
        int n = 2;
        tmp.addAll(arr.get(n + 0).subList(0, 3));
        tmp.addAll(arr.get(n + 1).subList(1, 2));
        tmp.addAll(arr.get(n + 2).subList(0, 3));
        System.out.println(tmp);  // [1, 1, 1, 1, 1, 1, 1]


        // Loop through windows (4x4 starting points)
        // **************
        List<Integer> sums = new ArrayList<>();

        for (int k=0; k<4; k++) {
            for (int i=0; i<4; i++) {

                List<Integer> hourglass = new ArrayList<>();

                hourglass.addAll(arr.get(i + 0).subList(k + 0, k + 3));
                hourglass.addAll(arr.get(i + 1).subList(k + 1, k + 2));
                hourglass.addAll(arr.get(i + 2).subList(k + 0, k + 3));

                // Sum the hourglass
                int sum = 0;
                for (int v : hourglass) sum += v;
                sums.add(sum);
            }
        }

        // Maximum of all hourglass sums
        int max = Collections.max(sums);

        System.out.println("Sums: " + sums);
        System.out.println("Max: " + max);
        // *******************
        // Sums: [7, 4, 3, 3, 4, 8, 6, 9, 2, 10, 7, 19, 0, 8, 6, 14]
        // Max: 19
    }
}
