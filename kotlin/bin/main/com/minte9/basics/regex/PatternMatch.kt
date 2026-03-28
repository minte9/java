/**
 * Regex in Kotlin is much simpler than in Java.
 * Kotlin has its own Regex, no need for Pattern and Matcher boilerplate.
 *
 * Use Regex("pattern") to create a pattern.
 * Use matches() to check if WHOLE string matches.
 * Use containsMatchIn() to check if ANY PART of the string matches.
 */

package com.minte9.basics.regex

fun main() {
    val regex = Regex("Version")
    val text = "Version 1.0"

    // Full match flag (entire string must match)
    val a = regex.matches(text)
    println(a)  // false

    // Partial match flag (search anywhere)
    val b = regex.containsMatchIn(text)
    println(b)  // true

    // Find match result
    val c = regex.find(text)
    println(c?.value)  // Version
    println(c?.range)  // 0..6
}

