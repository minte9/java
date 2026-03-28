/**
 * Kotlin supports:
 * - Multiline strings using triple quotes (""")
 * - String concatenation with +
 * - Joining strings with joinToString()
 *
 * Kotlin simplifies all of these compared to Java
 */
package com.minte9.basics.strings

fun main() {

    // Concatenation with +
    val a = "AAA " +
        "BBB"

    // Joining lines (similar to String.join in Java)
    val b = listOf(
        "CCC ",
        "DDD"
    ).joinToString("\n")

    // Multiline string using triple quoates
    // Kotlin has this built-in, no need for Java 15 text blocks
    val c = """
        EEE
        FFF
    """.trimIndent()

    println(a)  // AAA BBB
    println(b)  // CCC
                // DDD
    println(c)  // EEE
                // FFF
}