/**
 * In Kotlin, variables of primitive types (Int, Boolean, etc)
 * are pased and assigned by value - not by reference.
 *
 * When z = x, a copy of x'2 value is made.
 * Changing z does not affect x.
 */

package com.minte9.basics.variables

fun main() {
    var x = 7  // 00000111 in binary
    var z = x  // copy of x
    println("x == z ${x == z}")

    z = 0
    println("x != z &{x != z}")
}

/**
    x == z true
    x != z &{x != z}
 */