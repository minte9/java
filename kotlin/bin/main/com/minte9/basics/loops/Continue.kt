/**
 * In Kotlin, 'continue' skip the current iteration and moves to the next.
 *
 * You can also use labels to skip an iteration in an outer loop.
 */

package com.minte9.basics.loops

fun main() {
    print("Simple continue: ")
    for (i in 1..5) {
        if (i == 3) continue  // skip number 3
        print("$i ")
    }

    println("\nLabeled continue:")
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) {
                print(" -> continue outer at i=$i, j=$j\n")
                continue@outer  // skip to next iteration of outer loop
            }
            print("($i,$j)")
        }
        println()
    }
}

/**
    Simple continue: 1 2 4 5
    Labeled continue:
    (1,1)(1,2)(1,3)
    (2,1) -> continue outer at i=2, j=2
    (3,1)(3,2)(3,3)
 */