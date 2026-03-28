/**
 * Kotlin Collections - Key Concepts
 *
 * 1. Arrays (arrayOf(), IntArray, etc.)
 *      - Fixed size (cannot grow or shrink)
 *      - Useful for performance or numeric data
 *
 * 2. Immutable list (listOf())
 *      - Read-only: cannot add, remove or modify
 *      - Safer; used for constant sets of data
 *
 * 3. Mutable list (mutableOf())
 *      - Fully modifiable: add(), remove(), set(), etc.
 *      - Kotlin alternative to Java's ArrayList
 */

package com.minte9.collections.lists

fun main() {

    // Arrays
    val scores = arrayOf(85, 90, 78)
    scores[0] = 99
    println(scores.size)  // 3
    println(scores[0])  // 99
        // scores[3] = 4  // ❌ ERROR — size exceeded

    // Lists
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    println(days.size)  // 7
    println(days[0])  // Mon
        // days.add(4)  ❌ ERROR — immutable

    // Mutable Lists
    val cart = mutableListOf<String>()
    cart.add("apple")
    cart.add("banana")
    cart.add("chocolate")
    println(cart)  // [apple, banana, chocolate]

    cart.remove("banana")
    cart.add("milk")
    println(cart)  // [apple, chocolate, milk]
}