/**
 * STREAMS - PIPELINE MODEL
 * ------------------------
 * A stream pipeline consists of: 
 * 
 * 1) SOURCE
 *     - Creates the stream (collection, array, file, etc)
 * 
 * 2) INTERMEDIATE OPERATIONS
 *     - Transform or filter elements
 *     - Lazy (nothing happens yet)
 * 
 * 3) TERMINAL OPERATIONS
 *     - Produces a result or side-effect
 *     - Triggers execution of the pipeline 
 * 
 * WHAT HAPPENS STEP BY STEP
 * -------------------------
 * Even through the code is written top-to-bottom, exection happens
 * element-by-element, not step-by-step
 * 
 * Logical execution:
 * 1 -> filter -> skip
 * 2 -> filter -> skip
 * 3 -> filter -> map -> count
 * 4 -> filter -> map -> count
 * 5 -> filter -> map -> count
 */

package com.minte9.streams.pipeline_model;

import java.util.Arrays;
import java.util.List;

public class PipelineModel {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        long total = numbers.stream()            // SOURCE
                            .filter(n -> n > 2)  // INTERMEDIATE
                            .map(n -> n * 2)     // INTERMEDIATE
                            .count();            // TERMINAL
        System.out.println(total);  // 3
    }
}
