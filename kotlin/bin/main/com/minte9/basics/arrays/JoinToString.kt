/**
 * In Kotlin, array elements can be joined easily using joinToString().
 *
 * joinToString() is available on any collection of array.
 */

package com.minte9.basics.arrays

fun main() {
    val A = arrayOf("a", "b", "c")

    println(A.joinToString(", "))  // a, b, c
}