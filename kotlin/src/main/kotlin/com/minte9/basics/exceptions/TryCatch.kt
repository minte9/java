/**
 * Exceptions are one of the biggest differences from Java.
 *
 * In Kotlin there are no 'throws Exception' in function signature.
 * The compiler does not force try/catch.
 * You may catch exceptions, but you're never required to.
 *
 * This makes code much cleaner, but ...
 * the developer must be a bit more careful in Kotlin.
 *
 */

package com.minte9.basics.exceptions

fun main() {
    try {
        check("wrong"); // Correct code (for compiler)
    } catch (e: Exception) {
        print("${e.message}")  // Exception: Wrong value!
    }
}

fun check(s: String) {
    if (s.equals("wrong")) {
        throw Exception("Exception: Wrong value!")
    }
}