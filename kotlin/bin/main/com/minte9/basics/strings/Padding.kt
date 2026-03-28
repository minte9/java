/**
 * Kotlin supports padding trough:
 * - padStart() / padEnd()
 * - String.format() is still valid in Kotlin
 */
package com.minte9.basics.strings

fun main() {

    // Java style formatting
    val a = "0" + String.format("%5s", 123)
    val b = String.format("%6s", 123).replace(" ", "0")
    val c = String.format("%-6s", 123).replace(" ", "0")
    println(a)
    println(b)
    println(c)
    /*
        0  123
        000123
        123000
     */

    // Kotlin padding
    val x = "123".padStart(6, '0')
    val y = "123".padEnd(6, '0')
    println(x)
    println(y)
    /**
        000123
        123000
     */

    // Example - Aligning strings in columns (table)
    val names = listOf("Alice", "Bob", "Charlie")
    val ages = listOf(24, 32, 19)
    println("Name".padEnd(10) + "Age")
    println("-".repeat(14))
    for (i in names.indices) {
        val name = names[i].padEnd(10)
        val age = ages[i].toString().padStart(3)
        println(name + age)
    }
    /**
        Name      Age
        --------------
        Alice      24
        Bob        32
        Charlie    19
     */

}