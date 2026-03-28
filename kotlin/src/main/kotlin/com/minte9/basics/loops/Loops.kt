/**
 * In Kotlin, 'for' loops are simplified.
 *
 * The 'for' loops iterate directly over a range or collections.
 * No need for Java's enhanced-for syntax - it's unified.
 * The 'forEach' works with lambdas and method references.
 */

package com.minte9.basics.loops

fun main() {

    print("\nFor loop: ")
    for (i in 0..2) {  // inclusive range (0, 1, 2)
        print(i)
    }

    print("\nFor (over array): ")
    val nums = arrayOf(0, 1, 2)
    for (n in nums) {   // direct iteration, enhanced-for style
        print(n)
    }

    print("\nforEach with lambda: ")
    val A = listOf(0, 1, 2)
    A.forEach{ x -> print(x)}

    print("\nforEach with method reference: ")
    val B = listOf(0, 1, 2)
    B.forEach(::print)
}

/**
    For loop: 012
    For (over array): 012
    forEach with lambda: 012
    forEach with method reference: 012
 */