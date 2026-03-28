/**
 * In Kotlin, arrays are created using arrayOf().
 *
 * Memory is allocated automatically for the given number of elements.
 * Arrays have a fixed size.
 * Accessing an invalid index throws an exception.
 */
package com.minte9.basics.arrays

fun main() {
    // Declare and initiate
    val nums = IntArray(2);
    nums[0] = 1
    nums[1] = 2
    val names = arrayOf("John", "Marry", "Ana")

    println(nums[1])  // 2
    println(names[2])  // Ana

    try {
        names[3] = "Willy"
    } catch (ex: Exception) {
        println(ex.message)  // Index 3 out of bounds for length 3
    }
}