/**
 * In Kotlin, 'break' works the same way to exit from a loop.
 *
 * You can use labels to break (or continue) outer loops.
 * A label is defined with an identifier followed by '@'.
 */

package com.minte9.basics.loops

fun main() {
    val numbers = arrayOf(
        arrayOf(1,2,3),
        arrayOf(4,5,6),
        arrayOf(7,8,9),
    )

    println("Simple break:")
    for ((index, row) in numbers.withIndex()) {
        if (index == 2) break  // skip line 2 (last)
        row.forEach(::print)
        println()  // line break between rows
    }

    println("Labeled break:")
    outer@ for (i in numbers.indices) {
        inner@ for (j in numbers.indices) {
            print("$i:$j ")

            if (numbers[i][j] == 4) {
                println("Break from inner loop.")
                break@inner
            }

            if (numbers[i][j] == 8) {
                println("Break from outer loop.")
                break@outer
            }
        }
    }
}

/**
    Simple break:
    123
    456
    Labeled break:
    0:0 0:1 0:2 1:0 Break from inner loop.
    2:0 2:1 Break from outer loop.
 */