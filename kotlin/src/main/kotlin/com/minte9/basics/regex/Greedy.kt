/**
 * Kotlin uses the same regex engine as Java.
 *
 * Greedy vs Lazy (Ungreedy) quantifiers:
 *  - .+    greedy  → match longest possible
 *  - .+?   lazy    → match shortest possible
 */
package com.minte9.basics.regex

fun main() {
    val text = "extend cup end table"

    // Greedy
    var regex = Regex("e.+d")  // greedy
    regex.findAll(text).forEach {
        println(it.value)  // extend cup end
    }

    // Lazy (ungreedy)
    regex = Regex("e.+?d")  // ungreedy
    regex.findAll(text).forEach {
        println(it.value)  // extend
                           // end
    }
}