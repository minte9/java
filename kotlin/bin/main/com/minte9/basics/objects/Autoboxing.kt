/**
 * Kotlin has no separate wrapper classes (Java does).
 * It uses primitive types (Int, Double, etc) directly when possible,
 * and automatically boxes them if needed (in collections for example).
 *
 * Boxing: Int -> java.lang.Integer
 * Unboxing: java.langInteger -> Int
 *
 * This is automatically and invisible in Kotlin.
 */

package com.minte9.basics.objects

fun main() {
    val li = mutableListOf<Int>()
    for (i in 10 until 20) {
        li.add(i)  // automatic boxing
    }
    println(li[0]::class.java.name)  // java.lang.Integer

    val b: Int = li[0]  // automatic unboxing
    println(b)  // 10
}
