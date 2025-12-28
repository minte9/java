/**
 * Guava - Joiner example
 */

package com.minte9.joiner;
import com.google.common.base.Joiner;

public class GuavaJoiner {
    
    public static void main(String[] args) {
        
        Joiner joiner = Joiner.on("; ").skipNulls();
        System.out.println(
            joiner.join("a", "b", null, 1, 2) // a; b; 1; 2
        );
    }
}