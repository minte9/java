/**
 * Kotlin replaces Java's swith with modern statement 'when'.
 * It is more powerfull and flexible and it isn't available in Java.

 * It can be used as a statement (like if / switch),
 * or as an expression that return a value (Java couldn't until v.14+)
 *
 * No break needed in Kotlin, each branch ends automatically.
 */

package com.minte9.basics.`when`

fun main() {

    println("Simple example (like a Java switch):")
    val day = 3
    when (day) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        else -> println("Another day")
    }

    /** Java equivalent:
        int day = 3;
        switch (day) {
            case 1: System.out.println("Monday"); break;
            case 2: System.out.println("Tuesday"); break;
            case 3: System.out.println("Wednesday"); break;
            default: System.out.println("Another day");
        }
     */

    println("Example of returning a value:")
    val score = 85
    val grade = when (score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "F"
    }
    println("Grade: $grade")

    println("Example without a subject (acts like chained if/else)")
    val a = 10
    val b = 20
    when {
        a > b -> println("a is greater")
        a < b -> println("b is greater")
        else -> println("They are equal")
    }
}

/**
    Wednesday
    Example of returning a value:
    Grade: B
    Example without a subject (acts like chained if/else)
    b is greater
 */

