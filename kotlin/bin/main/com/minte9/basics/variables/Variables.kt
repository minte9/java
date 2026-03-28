/**
 * In Kotlin, function parameters are strongly typed.
 *
 * If you pass an argument of the wrong type,
 * the compiler will report a type mismatch error
 *
 * Return type is declared after parameters (n1: Int)
 * In Java is declared before (int n1)
 */

package com.minte9.basics.variables

class Math {
    fun sum(n1: Int, n2: Int): Int {
        return n1 + n2
    }

    // We could simplfy the function to a single-line expression
    fun multiply(n1: Int, n2: Int) = n1 * n2
}

fun main() {
    val math = Math()
    val sum = math.sum(1,2)
    val mlt = math.multiply(2, 3)
    // math.sum(1, "2")  // ❌ Type mismatch error

    println("sum(1,2) = $sum")  // sum(1,2) = 3
    println("mlt(2,3) = $mlt")  // mlt(2,3) = 6
}